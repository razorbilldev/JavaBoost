package com.namo.modules.multithread.exceptionHandlerInMultiThreadEnv;

import java.util.concurrent.CompletableFuture;

public class GlobalHandler {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((thread, ex) -> {
            System.out.printf("Global handler -> Thread %s died with: %s%n", thread.getName(), ex);
        });

        Thread thread1 = new Thread(new UncaughtExample(), "Worker-1");
        Thread thread2 = new Thread(()->{
            System.out.println("Davron");
        }, "Worker-2");
        thread1.start();
        thread2.start();
    }
}
