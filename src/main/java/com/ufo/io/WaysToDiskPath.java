package com.ufo.io;

import java.net.URISyntaxException;

public class WaysToDiskPath {

    public static void main(){
        System.out.println(System.getProperty("user.dir"));
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());
        try {
            System.out.println(Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
