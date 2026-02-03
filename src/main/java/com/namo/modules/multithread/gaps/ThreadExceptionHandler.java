package com.namo.modules.multithread.gaps;

public class ThreadExceptionHandler {
    public static void main(String[] args) {

        // ────────────────────────────────────────────────
        // Approach 1: Try-catch INSIDE the Runnable/Callable
        // ────────────────────────────────────────────────
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("Thread 1 running...");
                if (true) throw new RuntimeException("Boom inside try-catch!");
            } catch (Exception e) {
                System.out.println("Caught inside thread: " + e);
                // You can log, recover, notify main thread via queue, etc.
            }
        }, "T1-internal-catch");

        // ────────────────────────────────────────────────
        // Approach 2: UncaughtExceptionHandler (per thread)
        // ────────────────────────────────────────────────
        Thread t2 = new Thread(() -> {
            System.out.println("Thread 2 running...");
            throw new RuntimeException("Unhandled boom in T2!");
        }, "T2-uncaught");

        t2.setUncaughtExceptionHandler((thread, e) -> {
            System.out.printf("UncaughtExceptionHandler → Thread %s died because: %s%n",
                    thread.getName(), e.getMessage());
            // You can send to monitoring, restart logic, etc.
        });

        // ────────────────────────────────────────────────
        // Approach 3: Global default handler (affects NEW threads)
        // ────────────────────────────────────────────────
        Thread.setDefaultUncaughtExceptionHandler((thread, e) -> {
            System.out.printf("[GLOBAL] Thread %s crashed: %s%n",
                    thread.getName(), e);
        });

        // ────────────────────────────────────────────────
        // Daemon thread example
        // ────────────────────────────────────────────────
        Thread daemon = new Thread(() -> {
            try {
                while (true) {
                    System.out.println("Daemon is working...");
                    Thread.sleep(400);
                    throw new RuntimeException("Daemon crashed!");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Daemon-worker");

        daemon.setDaemon(true);   // ← very important
        // daemon.setUncaughtExceptionHandler(...)  // also possible

        // Start all threads
        t1.start();
        t2.start();
        daemon.start();

        System.out.println("Main thread is finishing...");
        // Main will exit → daemon is killed automatically
    }
}
