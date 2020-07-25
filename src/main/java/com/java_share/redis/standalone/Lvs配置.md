# 55.30配置:
global_defs {
   router_id LVS_Node1
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
    lb_algo rr
    lb_kind DR
    net_mask 255.255.255.0
    persistence_timeout 0
    protocol TCP

    real_server 10.211.55.20 80 {
        weight 1
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
        weight 1
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

virtual_server 10.211.55.100 6379 {
    delay_loop 6
    lb_algo rr
    lb_kind DR
    persistence_timeout 0
    protocol TCP
    real_server 10.211.55.20 6379 {
        weight 1
        TCP_CHECK {             #tcp健康检查
            connect_port 6379       #健康检查端口
        }
    }
    real_server 10.211.55.21 6379 {
        weight 1
        TCP_CHECK {             #tcp健康检查
            connect_port 6379       #健康检查端口
        }
    }
    real_server 10.211.55.20 6380 {
        weight 1
        TCP_CHECK {             #tcp健康检查
            connect_port 6380       #健康检查端口
        }
    }
    real_server 10.211.55.21 6380 {
        weight 1
        TCP_CHECK {             #tcp健康检查
            connect_port 6380       #健康检查端口
        }
    }
}

# 55.10配置:
global_defs {
   router_id LVS_Node2
}

vrrp_instance VI_1 {
    state BACKUP
    interface eth0
    virtual_router_id 51
    priority 90
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
    lb_algo rr
    lb_kind DR
    persistence_timeout 0
    protocol TCP


    real_server 10.211.55.20 80 {
        weight 1
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
        weight 1
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

virtual_server 10.211.55.100 6379 {
    delay_loop 6
    lb_algo rr
    lb_kind DR
    persistence_timeout 0
    protocol TCP

    real_server 10.211.55.20 6379 {
        weight 1
        TCP_CHECK {             #tcp健康检查
            connect_port 6379       #健康检查端口
        }
    }
    real_server 10.211.55.21 6379 {
        weight 1
        TCP_CHECK {             #tcp健康检查
            connect_port 6379       #健康检查端口
        }
    }
    real_server 10.211.55.20 6380 {
        weight 1
        TCP_CHECK {             #tcp健康检查
            connect_port 6380       #健康检查端口
        }
    }
    real_server 10.211.55.21 6380 {
        weight 1
        TCP_CHECK {             #tcp健康检查
            connect_port 6380       #健康检查端口
        }
    }
}