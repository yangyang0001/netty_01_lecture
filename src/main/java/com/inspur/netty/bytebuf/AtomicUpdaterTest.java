package com.inspur.netty.bytebuf;

/**
 * User: YANG
 * Date: 2019/5/4
 * Time: 18:17
 * Description: No Description
 */
public class AtomicUpdaterTest {

    public static void main(String[] args){
        Person person = new Person();
        for(int i = 0; i < 100; i++){
            Thread thread = new Thread(() -> {
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(person.age++);
            });
            thread.start();
        }
    }
}

class Person {
    int age = 1;
}
