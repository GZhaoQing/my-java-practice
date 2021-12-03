package com.ufo.threads;

import java.util.concurrent.*;

public class UseOfThreadPoolExecutor {
    public static void main(String[] args){
        startThreadPool();
    }
    public static void startSchedule(){
        ScheduledThreadPoolExecutor schedule = new ScheduledThreadPoolExecutor(2,new MySimpleThreadFactory("my-use-schedule-pool-%d"));
        schedule.schedule(()->System.out.println(Thread.currentThread().getName()),2,TimeUnit.SECONDS);
        try {
            System.out.println("wait to stop");
            Thread.sleep(10*1000);//等待几秒看到线程工作
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        schedule.shutdown();
    }
    /*
    * 此案例可以发现我们自定义的线程池超过maximumPoolSize+BlockingQueue长度后，会拒绝任务，如果没有异常处理会中断主线程。
    * 排队是不会触发新线程的创建的，实际工作线程数会按照corePoolSize执行，无法达到maximumPoolSize。
    * 当BlockingQueue排满时，才会触发新线程建立，工作线程达到maximumPoolSize。
    * */
    public static void startThreadPool(){
        ThreadFactory namedThreadFactory = new MySimpleThreadFactory("my-use-thread-pool-%d");
        ExecutorService pool = new ThreadPoolExecutor(
                1,
                10,
                100_000L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(128),
                namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());
        for(int i = 0;i<140;i++){
//            System.out.println("start"+i);
            int j=i;
            try{
                pool.execute(()-> {
                    System.out.println(Thread.currentThread().getName()+" work"+j);
                    try {
                        Thread.sleep(3*100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+" down"+j);
                });
            }catch(RejectedExecutionException e){
                e.printStackTrace();
                System.out.println("被拒绝了");
            }
        }
        try {
            System.out.println("wait to stop");
            Thread.sleep(60*1000);//等待几秒看到线程工作
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdown();
    }
}
