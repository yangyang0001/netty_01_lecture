本文件夹下的文件是用来复习handler_my_protocol下程序的

复习总结:
    1.tcp 在传递ByteBuf的时候存在拆包与粘包的过程,因此要在解码的过程中注意ByteBuf 中byte[] 数组的长度是多少!