## **环境准备**
笔者使用的os x系统,因此使用了Parallel Desktop虚拟机.
虚拟机安装系统 cent_os7.4(安装的时候选择web server版,不需要GUI)
先安装一台实例
yz30  10.211.55.30  DS(lvs负载均衡节点) 
yz10  10.211.55.10  DS备用节点 
yz20  10.211.55.20  RS20 后台业务节点(安装nginx)
yz21  10.211.55.21  RS21 后台业务节点(安装nginx)

安装完毕后,先连上宿主机的共享网络
1. 配置 vi /etc/sysconfig/network-scripts/ifcfg-eth0
TYPE=Ethernet
BOOTPROTO=static
NAME=eth0
UUID=d52761ce-2c2f-4623-9e55-1bd05e13cbd0
DEVICE=eth0
ONBOOT=yes
IPADDR=10.211.55.30
GATEWAY=10.211.55.1
NETMASK=255.255.255.0
设置静态的ip地址(4台机器不同),设置相同的网关和子网掩代码

2. 配置dns vi /etc/resolv.conf访问外网
3. 为了简单,注意关闭防火墙,重启网络服务systemctl restart network
4. 更新yum源 
    yum install -y epel-release
    yum update
    
5. 各种通用操作(比如说ssh免密登陆配置)之后,备份快照.
6. 利用快照新建3台一样的机器,修改他们的ip地址. 我这里是yz30,yz20,yz21,yz10
7. 在yz30,yz10上安装 yum install ipvsadm -y 
8. 在yz20,yz21上安装 nginx做为web后端服务器
    yum install nginx -y 
   配置: 新建 vi /etc/nginx/default.d/lvs.conf
   location /lvs {
   	default_type text/html;
   	return 200 "$host";
   }
   启动两台机器的nginx 
    访问 http://10.211.55.20:/lvs 返回 10.211.55.20
    访问 http://10.211.55.21:/lvs 返回 10.211.55.21

## **DS配置**
配置DS节点yz30, VIP
1. 先在DS节点开启IP转发 
    echo 1 > /proc/sys/net/ipv4/ip_forward
    
2. 配置VIP(虚拟ip地址)
    ifconfig eth0:6 10.211.55.100/24

3. 添加ipvs规则
    ipvsadm -A -t 10.211.55.100:80 -s wrr
	ipvsadm -a -t 10.211.55.100:80 -r 10.211.55.20 -g -w 1
	ipvsadm -a -t 10.211.55.100:80 -r 10.211.55.21 -g -w 1
    第一条代表进入负载VIP:80端口的流量 使用算法wrr(按权重轮询)
    后面两条是将VIP:80的流量转向对应的RS节点 -w 1 代表权重1

4. 简单的操作命令(还有很多其他功能可以操作调优,后面自己研究)
    ipvsadm -ln 查看规则
    ipvsadm -C  清空规则
    ipvsadm -S > xx.txt  保存规则文件
    ipvsadm -R < xx.txt  导入规则文件
    
到这一步,DS的VIP配置就好了.

## **RS配置**
分别在yz20,yz21做以下配置:

1. 设置VIP
    ifconfig lo:3 10.211.55.100/32
    route add -host 10.211.55.100 dev lo:3
    将VIP的流量通过lo:3接口发出去

2. 设置arp协议,
    echo 1 > /proc/sys/net/ipv4/conf/eth0/arp_ignore 
    echo 2 > /proc/sys/net/ipv4/conf/eth0/arp_announce 
    echo 1 > /proc/sys/net/ipv4/conf/all/arp_ignore 
    echo 2 > /proc/sys/net/ipv4/conf/all/arp_announce 
    

## **单点LVS部署完成**
测试:请求http://10.211.55.100/lvs
可以用LvsTest测试观察QPS情况



## **利用keepalived部署高可用的LVS集群**

1. 在yz30和yz10(备用节点)上安装keepalived.
    yum install keepalived -y
    
2. vi /etc/keepalived/keepalived.conf
我的yz30主节点配置如下,权重为yz20 10, yz21 8:
global_defs {
   router_id LVS_node1
}
vrrp_instance VI_1 {
    state MASTER
    interface eth0
    virtual_router_id 51
    priority 100
    advert_int 1
    authentication {
        auth_type PASS
        auth_pass 1111
    }
    virtual_ipaddress {
        10.211.55.100/24 dev eth0 label eth0:3
    }
}
virtual_server 10.211.55.100 80 {
    delay_loop 6
    lb_algo wrr
    lb_kind DR
    net_mask 255.255.255.0
    persistence_timeout 0
    protocol TCP
    real_server 10.211.55.20 80 {
        weight 10
        HTTP_GET {
            url {
              path /
              status_code 200
            }
            connect_timeout 3
            nb_get_retry 3
            delay_before_retry 3
        }
    }
    real_server 10.211.55.21 80 {
        weight 8
        HTTP_GET {
            url {
              path /
              status_code 200
            }
            connect_timeout 3
            nb_get_retry 3
            delay_before_retry 3
        }
    }
}
yz10备用节点配置如下:
主要修改了state,priority,权重为yz20 15,yz21 20,为了待会儿测试验证
global_defs {
   router_id LVS_node2
}
vrrp_instance VI_1 {
    state BACKUP
    interface eth0
    virtual_router_id 51
    priority 99
    advert_int 1
    authentication {
        auth_type PASS
        auth_pass 1111
    }
    virtual_ipaddress {
        10.211.55.100/24 dev eth0 label eth0:3
    }
}
virtual_server 10.211.55.100 80 {
    delay_loop 6
    lb_algo wrr
    lb_kind DR
    net_mask 255.255.255.0
    persistence_timeout 0
    protocol TCP
    real_server 10.211.55.20 80 {
        weight 15
        HTTP_GET {
            url {
              path /
              status_code 200
            }
            connect_timeout 3
            nb_get_retry 3
            delay_before_retry 3
        }
    }
    real_server 10.211.55.21 80 {
        weight 20
        HTTP_GET {
            url {
              path /
              status_code 200
            }
            connect_timeout 3
            nb_get_retry 3
            delay_before_retry 3
        }
    }
}

3. yz30清理之前配置的规则
    ipvsadm -C 
   删除之前配置的虚拟网卡接口
    ifconfig eth0:6 down 

4. yz30启动keepalived
    systemctl start keepalived 
    
5. LvsTest代码测试访问流量,可以发现yz20与yz21的QPS比例为5:4左右,符合配置预期

6. yz10备用节点启动
    启动完成后,可以看到因为yz30存活,执行 ifconfig, yz10并没有创建eth0:3虚拟VIP
    同时关闭yz30: systemctl stop keepalived 
    一会儿后,查看yz10,已经自动创建好了,eth0:3 并开始代理
    查看Test代码,发现流量比例发生变化为 3:4 符合预期. 
    同时也可以看到几乎是平稳过度,测试代码也没有报错.
    
    

## **踩坑记录**:

由于宿主机开了代理,并发测试时出现大量的Time_wait连接
    