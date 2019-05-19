package com.inspur.netty.decoration;

/**
 * User: YANG
 * Date: 2019/4/25
 * Time: 19:16
 * Description: No Description
 */
public class Decorator implements Component {

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void doSomething() {
        component.doSomething();
    }
}
