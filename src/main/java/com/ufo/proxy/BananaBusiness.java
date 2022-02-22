package com.ufo.proxy;

public class BananaBusiness implements Business{

    @Override
    public void approach() {
        System.out.println("提前接洽");
    }

    @Override
    public void contact() {
        System.out.println("建立联系");
    }

    @Override
    public void negotiate() {
        System.out.println("交易谈判");
    }
}
