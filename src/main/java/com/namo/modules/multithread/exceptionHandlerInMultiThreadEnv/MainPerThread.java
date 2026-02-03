package com.namo.modules.multithread.exceptionHandlerInMultiThreadEnv;

public class MainPerThread {
    public static void main(String[] args) {
        Thread t = new Thread(new UncaughtExample(), "Worker-1");
        t.setUncaughtExceptionHandler((thread, ex) -> {
            System.err.println("Uncaught in " + thread.getName() + ": " + ex);

        });
        t.start();

        System.out.println("Main continues");
    }
}
