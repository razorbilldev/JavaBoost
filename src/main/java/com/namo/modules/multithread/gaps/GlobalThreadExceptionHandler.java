package com.namo.modules.multithread.gaps;

public class GlobalThreadExceptionHandler {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((th, ex) -> {
            // maybe we can send to other Monitoring or Analysis module for Reporting
            System.err.println("[CRITICAL] Thread-" + th.getName() + "Error message: " + ex.getMessage());
            ex.printStackTrace(System.err);
        });

        Thread thread1 = new Thread(() -> {
            System.out.println("platform thread is running");
            throw new RuntimeException();
        }, "Worker#3");

        thread1.start();
    }
}
