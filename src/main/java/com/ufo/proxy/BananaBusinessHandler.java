package com.ufo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class BananaBusinessHandler implements InvocationHandler {

    Object target;

    public BananaBusinessHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().equals("approach")){
            System.out.println("找到香蕉供应商");
        }
        return method.invoke(target,args);
    }
}
