
任何程序都有:
0:标准输入
1:标准输出
2:报错输出

#演示虚拟文件系统
dd if=/dev/zero of=mydisk.img bs=1048576 count=100
losetup /dev/loop0 mydisk.img
mke2fs /dev/loop0
mount /dev/loop0 /mnt/xx
cd /mnt/xx
mkdir bin
mkdir lib64
cp /bin/bash ./bin/

显示bash依赖
ldd bash
	linux-vdso.so.1 =>  (0x00007ffe74d8a000)
	libtinfo.so.5 => /lib64/libtinfo.so.5 (0x00007fdeaec5a000)
	libdl.so.2 => /lib64/libdl.so.2 (0x00007fdeaea56000)
	libc.so.6 => /lib64/libc.so.6 (0x00007fdeae688000)
	/lib64/ld-linux-x86-64.so.2 (0x00007fdeaee84000)

cp /lib64/{libtinfo.so.5,libdl.so.2,libc.so.6,ld-linux-x86-64.so.2} lib64/

进入当前目录bash
chroot ./

对比bash进程id号, 此时/tt.txt文件落在了/mnt/xx/tt.txt
echo $$ > /tt.txt

#演示文件描述符
准备文件ee.txt,输入几行内容
执行 
exec 8< ee.txt    文件定向到描述符8
cd /proc/$$/fd 可以观察到fd 8

lsof -op $$ | grep ee.txt
bash    6412 root    8r   REG    7,0   0t0       13 /mnt/yz/ee.txt
偏移量0t0

read a 0<& 8    每次读fd 8一行给a的标准输入
echo $a  得到首行内容

lsof -op $$ | grep ee.txt
bash    6412 root    8r   REG    7,0   0t12       13 /mnt/yz/ee.txt
偏移量发生变化0t12

关闭fd
exec 8>&-

socket的fd
exec 10<> /dev/tcp/www.baidu.com/80
lsof -op $$
exec 10>&-


# 管道
{ a=9; echo "fgdfg" ; } | cat
管道会新启动一个进程, 所以父进程的a不会被改变

# IO
pcstat 观察pageCache的缓存情况


vm.dirty_background_ratio = 90
vm.dirty_ratio = 90
vm.dirty_writeback_centisecs = 5000
vm.dirty_expire_centisecs = 30000

vm.dirty_background_ratio = 0
vm.dirty_background_bytes = 1048576
vm.dirty_ratio = 0
vm.dirty_bytes = 1048576


dirty 内存与磁盘数据不一致,内存修改过
vi  /etc/sysctl.conf 修改
vm.dirty_background_ratio = 90  脏数据比例cache90%
vm.dirty_ratio = 90
vm.dirty_writeback_centisecs = 500  指定多长时间做一次脏数据写回操作，默认5s
vm.dirty_expire_centisecs = 3000  指定脏数据能存活的时间，单位为百分之一秒  默认30s

sysctl -p  使配置生效

java OSFileIO 0  写入文件
虚拟机断电,会发现文件没有写入

使用strace监控进程的系统调用
strace -tt -f -o ./output.log -p {pid}
可以发现,有buffer的系统调用次数更少,性能更强
strace 监听java程序
strace -ff -o out java Test  执行java并监听



# java中的mmap

    java中的FileChannel，提供了map和force方法，map创建文件和内存的映射， 
        返回一个MappedByteBuffer，这是



# socket参数含义

server.bind(new InetSocketAddress(8821), BACK_LOG);
backlog设置接入等待accept连接队列的最大长度.
做实验可以发现,超过的连接数等待一段时间会抛出timeout:
java.net.ConnectException: Operation timed out (Connection timed out)


TCP_NODELAY  tcp延迟, 启用意味着禁用nagle算法, 禁用意味着开启nagle算法
启用时,按照内核调度该发送就发送了,比较零散
禁用时根据nagle算法,会尽量攒一个大点的包发送,会有延迟
https://blog.csdn.net/lclwjl/article/details/80154565



# TCP拥塞控制
https://zhuanlan.zhihu.com/p/76023663
