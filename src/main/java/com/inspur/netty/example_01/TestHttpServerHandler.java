package com.inspur.netty.example_01;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * User: YANG
 * Date: 2019/4/22
 * Time: 14:05
 * Description: No Description
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    //读取客户端发送过来的数据,并且响应客户端,给客户端返回一定的信息
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        //记住这个地方有一个没有解决的坑!(会有多个请求进入这个方法中,现在将其拿入到 if 代码块中)
//        System.out.println("channelRead0 invoked");
        System.out.println("msg class ---------------------------------------------:" + msg.getClass());

        if(msg instanceof HttpRequest){
            System.out.println("channelRead0 invoked------------------!");

            HttpRequest httpRequest = (HttpRequest) msg;
            System.out.println("HttpRequest Method Name --------------:" + httpRequest.method().name());
            URI uri = new URI(httpRequest.uri());
            System.out.println("HttpRequest URI path -----------------:" + uri.getPath());

            if("/favicon.ico".equals(uri.getPath())){
                System.out.println("请求 favicon.ico");
                return;
            }


            ByteBuf content = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK, content);

            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            ctx.writeAndFlush(response);
            ctx.channel().close();

            System.out.println("-------------------------------------------------------------------------------------------------------");
        }
    }

    //为了更好的理解,这个 SimpleChannelInBoundHandler 我们逐个重写内部的方法, 加深对 SimpleChannelInBoundHandler 的理解

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel active");
        super.channelActive(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel registered");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel unregistered");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel inactive");
        super.channelInactive(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler added");
        super.handlerAdded(ctx);
    }
}
