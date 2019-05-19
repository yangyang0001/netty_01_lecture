package com.inspur.netty.decoration;

/**
 * User: YANG
 * Date: 2019/4/25
 * Time: 19:18
 * Description: No Description
 */
public class Decorator1 extends Decorator {

    public Decorator1(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        super.doSomething();
        this.doAnthorThing();
    }

    public void doAnthorThing(){
        System.out.println("Decortor1");
    }
}
