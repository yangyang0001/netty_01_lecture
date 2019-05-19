package com.inspur.netty_source_deep;

/**
 * User: YANG
 * Date: 2019/4/29
 * Time: 13:50
 * Description: No Description
 */
public class TestObject {

    public static void main(String[] args){

//        List<Integer> firstList = Arrays.asList(1, 3, 4 ,5 ,6, 9, 100);
//        List<Integer> secondList = Arrays.asList(0, 3, 33 ,5 ,66, 15, 2);
//
//        List<List<Integer>> lastList = Arrays.asList(firstList, secondList);
//
//        lastList.stream().flatMap(list -> list.stream())
//                .filter(item -> !(firstList.contains(item) && secondList.contains(item)))
//                .sorted(Integer::compareTo)
//                .forEach(System.out::println);
//
//
//        System.out.println(4 << 1);


        TestObject test = new TestObject();
        long currentTime = System.currentTimeMillis();
        System.out.println(test.testStringBuffer());
        System.out.println("Buffer耗时:" + (System.currentTimeMillis() - currentTime));

        currentTime = System.currentTimeMillis();
        System.out.println(test.testAddString());
        System.out.println("Add 耗时  :" + (System.currentTimeMillis() - currentTime));




    }

    public String testStringBuffer(){
        StringBuffer stringBuffer = new StringBuffer("");
        String str1 = "aaa";
        String str2 = "bbb";
        String str3 = "ccc";
        String str4 = "ddd";
        String str5 = "eee";
        String str6 = "fff";
        String str7 = "fff";
        String str8 = "fff";
        String str9 = "fff";
        String str0 = "fff";

        stringBuffer.append(str1)
                .append(str2)
                .append(str3)
                .append(str4)
                .append(str5)
                .append(str6)
                .append(str7)
                .append(str8)
                .append(str9)
                .append(str0);
        return stringBuffer.toString();
    }

    public String testAddString(){
        String str1 = "aaa";
        String str2 = "bbb";
        String str3 = "ccc";
        String str4 = "ddd";
        String str5 = "eee";
        String str6 = "fff";
        String str7 = "fff";
        String str8 = "fff";
        String str9 = "fff";
        String str0 = "fff";
        String str = str1 + str2 + str3 + str4 + str5 + str6 + str7 + str8 + str9 + str0;

        return str;
    }
}
