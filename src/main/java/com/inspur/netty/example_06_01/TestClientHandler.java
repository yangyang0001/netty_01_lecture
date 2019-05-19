package com.inspur.netty.example_06_01;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * User: YANG
 * Date: 2019/4/23
 * Time: 21:11
 * Description: No Description
 */
public class TestClientHandler extends SimpleChannelInboundHandler<PersonDataInfo.MessageInfo>{


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonDataInfo.MessageInfo messageInfo) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        int random = new Random().nextInt(3) + 1;
        PersonDataInfo.MessageInfo messageInfo = null;

        switch (random) {
            case 1 :
                PersonDataInfo.Person person = PersonDataInfo.Person.newBuilder().setName("zhangsan").setAge(22).setGradename("高三八班").build();
                messageInfo = PersonDataInfo.MessageInfo.newBuilder()
                        .setDataType(PersonDataInfo.MessageInfo.DataType.PersonType)
                        .setPerson(person)
                        .build();
                break;

            case 2 :
                PersonDataInfo.Dog dog = PersonDataInfo.Dog.newBuilder().setName("gouzi").setAge(4).build();
                messageInfo = PersonDataInfo.MessageInfo.newBuilder()
                        .setDataType(PersonDataInfo.MessageInfo.DataType.DogType)
                        .setDog(dog)
                        .build();
                break;

            case 3 :
                PersonDataInfo.Cat cat = PersonDataInfo.Cat.newBuilder().setName("kitty").setCity("北京").build();
                messageInfo = PersonDataInfo.MessageInfo.newBuilder()
                        .setDataType(PersonDataInfo.MessageInfo.DataType.CatType)
                        .setCat(cat)
                        .build();
                break;
        }

        channel.writeAndFlush(messageInfo);
    }
}
