package com.ufo.threads;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AEasyBatchProcess {
    public static void main(String[] args){
        ExecutorService exe = null;
        try {
            exe= Executors.newCachedThreadPool();
//			exe=Executors.newFixedThreadPool(10);//or another pool
            char[] a_char_array = "1234".toCharArray();
            for(char a_char: a_char_array){
                CompletableFuture.supplyAsync(()->{
                    return doNextProcess(a_char);//you can print this: Thread.currentThread().getId(); to observe something
                },exe).thenAccept(res->{System.out.println(res);});//res is returned from doNextProcess()
            }
        } finally {
            exe.shutdown();
        }
    }

    private static char doNextProcess(char i) {
        try {
            Thread.sleep(2000);//do something
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i+","+(int)i+","+"doNextProcess!");
        return i;//TODO add something
    }
}
