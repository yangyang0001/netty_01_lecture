package com.inspur.netty.handler_protocol;

/**
 * User: YANG
 * Date: 2019/5/6
 * Time: 9:54
 * Description: No Description
 */
public class StudentProtocol {

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
