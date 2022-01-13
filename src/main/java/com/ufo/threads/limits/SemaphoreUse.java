package com.ufo.threads.limits;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Semaphore;

public class SemaphoreUse {
    static long countL=0;//多线程不安全的计数器
    //信号可以用于限流
    public static void  main(String[] args){
        Semaphore s=new Semaphore(10);//限制并发数，可以看作是可多次分享的锁
        Thread[] allThreads = new Thread[100];
        for (int index = 0; index < allThreads.length; index++) {
            allThreads[index] = new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    try {
                        s.acquire();//permits+1
                        countL++;
                        System.out.println(Thread.currentThread() + ":count:" + countL);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        s.release();//permits-1
                    }

                }
            });
        }
        for (Thread t : allThreads) t.start();
        for (Thread t : allThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
