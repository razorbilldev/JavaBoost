package com.namo.modules.multithread.gaps;

/*
    Local try-catch: exception handler within task layer
    Usage: Handle predictable/expected exceptions in that specific task
 */
public class LocalExceptionHandler implements Runnable {
    private final String str;

    public LocalExceptionHandler(String str) {
        this.str = str;
    }

    @Override
    public void run() {
        try {
            System.out.println("The length of string: " + str.length());
        } catch (NullPointerException e) {
            System.err.println("[CRITICAL] Thread-" + Thread.currentThread().getName() + " " + "Error message: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new LocalExceptionHandler(null),"Worker#1");
        thread.start();
    }
}
