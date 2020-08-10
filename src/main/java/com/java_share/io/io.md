
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