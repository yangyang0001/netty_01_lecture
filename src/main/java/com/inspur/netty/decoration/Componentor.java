package com.inspur.netty.decoration;

/**
 * User: YANG
 * Date: 2019/4/25
 * Time: 19:14
 * Description: No Description
 */
public class Componentor implements Component {
    @Override
    public void doSomething() {
        System.out.println("component");
    }
}
