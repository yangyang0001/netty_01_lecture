package com.inspur.netty.decoration;

/**
 * User: YANG
 * Date: 2019/4/25
 * Time: 19:27
 * Description: No Description
 */
public class TestDecorator {

    public static void main(String[] args){

        Component component = new Decorator1(new Decorator(new Componentor()));

        component.doSomething();;


    }
}
