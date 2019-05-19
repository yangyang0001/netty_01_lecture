package com.inspur.netty_source_deep;

import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;

/**
 * User: YANG
 * Date: 2019/4/29
 * Time: 12:32
 * Description: No Description
 */
public class TestNioEventLoopGroup {

    public static void main(String[] args){

        int number = Math.max(1, SystemPropertyUtil.getInt(
                "io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));

        System.out.println(number);


    }
}
