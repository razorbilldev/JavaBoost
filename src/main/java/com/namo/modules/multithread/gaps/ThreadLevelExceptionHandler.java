package com.namo.modules.multithread.gaps;

/*
 * Per-Thread Uncaught Exception Handler
 *
 * Purpose:
 *   Catches exceptions that were not handled inside the thread's run() method
 *   (i.e. unexpected / unpredicted runtime errors that would otherwise kill the thread silently).
 *
 * When it is triggered:
 *   - Only for exceptions that escape the run() method completely
 *   - After all try-catch blocks inside run() have failed to catch it
 *
 * Scope:
 *   - Applies only to this specific Thread instance
 *   - Does NOT affect other threads
 *
 * Recommended usage:
 *   - Logging critical failures
 *   - Collecting diagnostics (stack trace, thread name, state)
 *   - Optional: graceful shutdown or notification
 *
 * NOT for:
 *   - Expected / business-logic exceptions → handle those with try-catch inside run()
 *
 * Official behavior (JDK 21):
 *   Thread.setUncaughtExceptionHandler() → called before thread group handler
 *   If no per-thread handler → falls back to ThreadGroup.uncaughtException()
 */
public class ThreadLevelExceptionHandler {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("Hello there, we are inside " + Thread.currentThread().getName() + " thread!!!");
            int invalid = 10000 / 0;
            System.out.println("Result: " + invalid);
        }, "Worker#2");

        thread.setUncaughtExceptionHandler((t, ex) -> {
            System.err.println("[CRITICAL] Thread: " + t.getName() + " terminated");
            ex.printStackTrace(System.err);
        });

        thread.start();
    }
}