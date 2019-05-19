package com.inspur.netty.example_03;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * User: YANG
 * Date: 2019/4/22
 * Time: 22:19
 * Description: No Description
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String>{

    //保存所有与服务端连接的 channel
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        Channel channel = ctx.channel();

        channelGroup.forEach(ch -> {
            if(channel != ch){
                ch.writeAndFlush(channel.remoteAddress() + " 发送消息:" + s + "\n");
            } else {
                channel.writeAndFlush("[自己]" + s + "\n");
            }
        });

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        channelGroup.writeAndFlush("[服务器-]," + channel.remoteAddress() + "加入\n");

        channelGroup.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
//        channelGroup.remove(channel);     //这句话 会自动执行的! 这里先不用太关注
        channelGroup.writeAndFlush("[服务器-]," + channel.remoteAddress() + "断开\n");
        System.out.println("channelGroup.size -----------------------:" + channelGroup.size());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //下面注释的三行, 是 client -> server1 -> server2 时, server1中如何连接 server2时的一种场景
//        Bootstrap bootstrap = new Bootstrap();
//        bootstrap.group(ctx.channel().eventLoop()).channel(NioSocketChannel.class).handler(null);
//        bootstrap.connect("localhost", 8899).sync();

        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " 上线了");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " 下线了");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
