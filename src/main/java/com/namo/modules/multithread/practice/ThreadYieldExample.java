package com.namo.modules.multithread.practice;

public class ThreadYieldExample {
    public static void main(String[] args) {
        Runnable task1 = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Inside Thread-1: " + " iteration - " + i);
                Thread.yield();
            }
        };
        Runnable task2 = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Inside Thread-2");
            }
        };
        Thread thread_one = new Thread(task1);
        Thread thread_two = new Thread(task2);

        thread_one.start();
        thread_two.start();
    }
}
