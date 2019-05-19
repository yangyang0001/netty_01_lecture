package com.inspur.netty.bytebuf;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * volatile 作用:
 *      1.防止执行指令的重排序
 *      2.多线程之间的共享变量的可见性
 * 针对AtomicIntegerFieldUpdater 中修改的 类型必须保证以下几点内容:
 *      1.必须是 volatile int 非static类型的
 */
public class AtomicIntegerFieldUpdaterTest {

    public static void main(String[] args) throws Exception{
        A object = new A();
        AtomicIntegerFieldUpdater<A> updater = AtomicIntegerFieldUpdater.newUpdater(A.class, "aa");

        for(int i =0 ;i < 10 ;i++){
            Thread thread = new Thread(() -> {
                try {
                    Thread.currentThread().sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(updater.incrementAndGet(object));
            });
            thread.start();
        }


        Thread.currentThread().sleep(1000);
        System.out.println("object aa ---------------:" + object.getAa());
    }
}

class A {

    public volatile int aa = 0;

    public int getAa() {
        return aa;
    }

    public void setAa(int aa) {
        this.aa = aa;
    }
}
