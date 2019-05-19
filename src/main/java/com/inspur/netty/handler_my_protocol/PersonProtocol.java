package com.inspur.netty.handler_my_protocol;

/**
 * 自定义传递数据的协议,就是给某种传递的数据进行 定制格式而已
 */
public class PersonProtocol {

    private int length;
    private byte[] content;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
