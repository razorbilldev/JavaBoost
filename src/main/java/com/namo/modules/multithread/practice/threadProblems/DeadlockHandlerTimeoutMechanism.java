package com.namo.modules.multithread.practice.threadProblems;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockHandlerTimeoutMechanism {
    private static final Lock narrowBridge1 = new ReentrantLock();
    private static final Lock narrowBridge2 = new ReentrantLock();

    public static void main(String[] args) {
        Runnable anneTask = () -> {
            while (true) {
                try {
                    if (narrowBridge1.tryLock(1, TimeUnit.SECONDS)) {
                        System.out.println("Anne: Holding part 1 of the bridge...");
                        try {
                            Thread.sleep(500);
                            if (narrowBridge2.tryLock(1, TimeUnit.SECONDS)) {
                                try {
                                    System.out.println("Anne: Holding part 1 and part 2 of the bridge...");
                                    break; // success
                                } finally {
                                    narrowBridge2.unlock();
                                }
                            } else {
                                System.out.println("Anne: Could not acquire the lock of the part 2.");
                            }
                        } finally {
                            narrowBridge1.unlock();
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        };
        Runnable jowTask = () -> {
            while (true) {
                try {
                    if (narrowBridge2.tryLock(1, TimeUnit.SECONDS)) {
                        System.out.println("Jow: Holding part 2 of the bridge...");
                        try {
                            Thread.sleep(500);
                            if (narrowBridge1.tryLock(1, TimeUnit.SECONDS)) {
                                try {
                                    System.out.println("Jow: Holding part 2 and part 1 of the bridge...");
                                    break; // success
                                } finally {
                                    narrowBridge1.unlock();
                                }
                            } else {
                                System.out.println("Jow: Could not acquire the lock of the part 1.");
                            }
                        } finally {
                            narrowBridge2.unlock();
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        };

        Thread.ofPlatform().start(anneTask);
        Thread.ofPlatform().start(jowTask);
    }
}
