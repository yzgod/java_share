# centos 安装 docker

### 1.安装工具和驱动包

```shell
yum install -y yum-utils device-mapper-persistent-data lvm2
```



### 2.配置国内yum源

```sh
yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo

yum makecache fast
```



### 3.安装docker

```
yum -y install docker-ce
```



### 4.启动docker

```
service docker start
docker version
```



### 5.hello world

```
docker pull hello-world

docker run hello-world
```

