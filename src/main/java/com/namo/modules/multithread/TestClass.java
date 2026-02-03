package com.namo.modules.multithread;

public class TestClass {
    public static void main(String[] args) {
        Thread.UncaughtExceptionHandler exceptionHandler = (t, e) -> {
            System.out.println("Thread name: " + t.getName() + " exception message: " + e.getMessage());
        };
        SampleThread thread = new SampleThread();
        thread.setUncaughtExceptionHandler(exceptionHandler);
        thread.start();
    }
}
