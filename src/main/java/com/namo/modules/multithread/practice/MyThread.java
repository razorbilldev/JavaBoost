package com.namo.modules.multithread.practice;

public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("RUNNING"); // RUNNING
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Cause: " + e.getCause());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread();
        System.out.println(thread.getState()); // NEW
        thread.start();
        System.out.println(thread.getState()); // RUNNABLE
        Thread.sleep(120);
        System.out.println(thread.getState()); // TIMED_WAITING
        thread.join();
        System.out.println(thread.getState()); // TERMINATED
    }
}