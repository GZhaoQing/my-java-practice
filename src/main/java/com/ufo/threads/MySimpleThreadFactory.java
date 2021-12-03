package com.ufo.threads;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/*
* BasicThreadFactory简化版
* */
public class MySimpleThreadFactory implements ThreadFactory {
    private final AtomicLong threadCounter;
    private final ThreadFactory factory;
    private final String namingPattern;
    private final Integer priority;//暂不使用
    private final Boolean daemon;

    public MySimpleThreadFactory(String namingPattern) {
        this.factory = Executors.defaultThreadFactory();
        this.namingPattern = namingPattern;
        this.priority = null;//暂不使用
        this.daemon=true;
        this.threadCounter = new AtomicLong();
    }

    @Override
    public Thread newThread(final Runnable r) {
        Executors.defaultThreadFactory();
        final Thread thread = getFactory().newThread(r);
        initializeThread(thread);

        return thread;
    }

    private ThreadFactory getFactory() {
        return factory;
    }

    private void initializeThread(final Thread thread) {

        if (getNamingPattern() != null) {

            final Long count = Long.valueOf(threadCounter.incrementAndGet());
            thread.setName(String.format(getNamingPattern(), count));
        }

        if (getUncaughtExceptionHandler() != null) {//暂不使用
            thread.setUncaughtExceptionHandler(getUncaughtExceptionHandler());
        }

        if (getPriority() != null) {//暂不使用
            thread.setPriority(getPriority().intValue());
        }

        if (getDaemonFlag() != null) {
            thread.setDaemon(getDaemonFlag().booleanValue());
        }
    }

    private String getNamingPattern() {
        return namingPattern;
    }

    private Thread.UncaughtExceptionHandler getUncaughtExceptionHandler() {
        return null;
    }

    private Integer getPriority() {
        return priority;
    }

    private Boolean getDaemonFlag() {
        return daemon;
    }
}
