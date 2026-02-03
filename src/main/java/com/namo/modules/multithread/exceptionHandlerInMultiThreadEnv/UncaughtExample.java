package com.namo.modules.multithread.exceptionHandlerInMultiThreadEnv;

public class UncaughtExample implements Runnable{
    @Override
    public void run() {
        System.out.println("Thread started: " + Thread.currentThread().getName());
        String s = null;
        s.length();
    }
}
