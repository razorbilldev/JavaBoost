package com.namo.modules.multithread.practice;

public class MyThreadPriority extends Thread {
    public MyThreadPriority(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("Thread is running...");
        for (int i = 1; i <= 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println(Thread.currentThread().getName() + " - Priority: " + Thread.currentThread().getPriority() + " - count: " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        MyThreadPriority low = new MyThreadPriority("Low Priority Thread");
        MyThreadPriority norm = new MyThreadPriority("norm Priority Thread");
        MyThreadPriority max = new MyThreadPriority("Max Priority Thread");

        low.setPriority(Thread.MIN_PRIORITY);
        norm.setPriority(Thread.NORM_PRIORITY);
        max.setPriority(Thread.MAX_PRIORITY);

        low.start();
        norm.start();
        max.start();
    }
}
