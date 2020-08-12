
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



# java中的mmap

    java中的FileChannel，提供了map和force方法，map创建文件和内存的映射， 
        返回一个MappedByteBuffer，这是一个DirectBuffer，其中包含一个内
        存地址，然后可用就做一些读写操作。 
    
    还有另外一个方法是force，是将内存的更新的内容刷到磁盘中。 
    在这里抛出一个问题，force是必须调用的，如果不调用force会怎样。
     
    原来force是调用的fdatasync(fsync)，那这么说mmap
    只是在进程空间分配一个内存地址，真实的内存还是使用的pagecache。
    所以force是调用fsync将dirty page刷到磁盘中，但mmap还有共享之类的实现起来应该很复杂。

    1. mmap，底层还是走的BufferedIO，好处大概是减少了内核态和用户态的内存拷贝。 
    2. force，参数为true调用fsync，false调用fdatasync，fdatasync只刷数据不刷meta数据 
    3. 即使不调用force，内核也会定期将dirty page刷到磁盘，默认是30s