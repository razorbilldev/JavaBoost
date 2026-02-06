package com.namo.modules.multithread.practice;

public class TestRunnable {
    public static void main(String[] args) {
        WorldRunnable worldRunnable = new WorldRunnable();
        Thread thread = new Thread(worldRunnable);
        thread.start();
        System.out.println("Main thread...");
    }
}
