package com.inspur.netty.protobuf;

/**
 * User: YANG
 * Date: 2019/4/23
 * Time: 17:08
 * Description: No Description
 */
public class ProtoBufTest {

    public static void main(String[] args) throws Exception {

        DataInfo.Student student1 = DataInfo.Student.newBuilder()
                .setName("zhangsan")
                .setAge(20)
                .setAddress("北京")
                .build();

        byte[] student2ByteArray = student1.toByteArray();

        DataInfo.Student student2 = DataInfo.Student.parseFrom(student2ByteArray);

        System.out.println(student2.getName());
        System.out.println(student2.getAge());
        System.out.println(student2.getAddress());


    }
}
