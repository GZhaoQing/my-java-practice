package com.ufo.threads.limits;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Phaser;

public class BarrierInThreadUse {

    public static void  main(String[] args){
//        barrierUse();
        phaserUse();
    }
    static long countL=0;//多线程不安全的计数器

    //用栅栏让所有线程轮流执行
    public static void barrierUse(){
        CyclicBarrier cb=new CyclicBarrier(100,()->{
            System.out.println("栅栏破了！");
        });
        Thread[] allThreads = new Thread[100];
        for (int index = 0; index < allThreads.length; index++) {
            allThreads[index] = new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    countL++;
                    System.out.println(Thread.currentThread() + ":count:" + countL);
                    try {
                        cb.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        for (Thread t : allThreads) t.start();
    }

    //phaser是可重复使用的栅栏，可以在分阶段、线程变动场景用到。
    public static void phaserUse(){
        Phaser phase=new Phaser(5);

        Thread[] allThreads = new Thread[100];
        for (int index = 0; index < allThreads.length; index++) {
            allThreads[index] = new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    countL++;
                    System.out.println(Thread.currentThread() + ":count:" + countL);
                    phase.arriveAndAwaitAdvance();
                    if(i%10==0){//phase拦截数量不断增加，大于100后,所有线程都阻塞了
                        phase.register();//phase同步的数量是可变的
                        System.out.println(phase.getRegisteredParties());
                    }
                }
            });
        }
        for (Thread t : allThreads) t.start();
    }
}
