package com.inspur.netty.protobuf;

/**
 * User: YANG
 * Date: 2019/4/23
 * Time: 19:35
 * Description: No Description
 */
public class MyDataInfoTest {

    public static void main(String[] args) throws Exception{

        MyDataInfo.Person person = MyDataInfo.Person.newBuilder()
                .setName("zhangsan")
                .setAge(30)
                .setGradename("高三八班")
                .build();


        byte[] person2ByteArray = person.toByteArray();


        MyDataInfo.Person person1 = MyDataInfo.Person.parseFrom(person2ByteArray);

        System.out.println(person1.getName());
        System.out.println(person1.getAge());
        System.out.println(person1.getGradename());

    }
}
