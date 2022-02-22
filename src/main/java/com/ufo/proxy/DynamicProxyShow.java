package com.ufo.proxy;

import java.lang.reflect.Proxy;

public class DynamicProxyShow {

    public static void main(String[] args){
        Business obj = new BananaBusiness();
        ClassLoader cl=obj.getClass().getClassLoader();
        Class<?>[] clazz=obj.getClass().getInterfaces();
        Business myProxy = (Business) Proxy.newProxyInstance(cl, clazz, new BananaBusinessHandler(obj));
        myProxy.approach();
        myProxy.contact();
        myProxy.negotiate();
    }
}
