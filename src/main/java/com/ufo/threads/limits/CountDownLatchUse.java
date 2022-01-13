package com.ufo.threads.limits;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.LongAdder;

// CountDownLatch的简单案例，作为计数器。
// 小白提醒：注意CountDownLatch不是锁哦，执行多次可以看到案例中的countL是可能数不到最大值的。
public class CountDownLatchUse {
    static long countL=0;//多线程不安全的计数器
    final static LongAdder adder=new LongAdder();//安全的计数器
    public static void  main(String[] args)  {
        sameWithJoin();
    }

    //通过await的特性，实现“发令枪”。和CyclicBarrier有类似
    public static void startAtSameTime()  {
        CountDownLatch cdl=new CountDownLatch(1);
        Thread[] allThreads = new Thread[100];
        for (int index = 0; index < allThreads.length; index++) {
            allThreads[index] = new Thread(() -> {
                try {
                    cdl.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 100; i++) {
                    countL++;
                    System.out.println(Thread.currentThread() + ":count:" + countL);
                }
            });
        }
        for (Thread t : allThreads) t.start();
        cdl.countDown();//开始！
        for (Thread t : allThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        System.out.println(adder.sum());
        System.out.println(countL);
    }

    //代替Thread的join，实现任务合并
    public static void sameWithJoin(){
        CountDownLatch cdl=new CountDownLatch(100);
        Thread[] allThreads = new Thread[100];
        for (int index = 0; index < allThreads.length; index++) {
            allThreads[index] = new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    countL++;
                    System.out.println(Thread.currentThread() + ":count:" + countL);
                }
                cdl.countDown();
            });
        }
        for (Thread t : allThreads) t.start();
        //相当于join了
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println(adder.sum());
        System.out.println(countL);
    }
}
