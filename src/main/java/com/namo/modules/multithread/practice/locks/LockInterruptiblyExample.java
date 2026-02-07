package com.namo.modules.multithread.practice.locks;

import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptiblyExample {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("Thread-1 got the lock");
                Thread.sleep(5000); //hold the lock
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("Thread-1 released the lock");
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                System.out.println("Thread-2 trying to get lock... ");
                lock.lockInterruptibly();
                try {
                    System.out.println("Thred-2 got the lock");

                } finally {
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        Thread.sleep(100);
        t2.start();
        Thread.sleep(1000);
        t2.interrupt();
    }
}
