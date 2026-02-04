package com.namo.modules.multithread.gaps;

/*
    Simple Demo for uncaught exception within the task
    NOTE: it seems a bit simple can u modify the code to make it more realistic.
    because the str we can check it not to be null within construction. In real world what kind of issues we might have for such cases
 */
public class LocalExceptionHandler implements Runnable {
    private final String str;

    public LocalExceptionHandler(String str) {
        this.str = str;
    }

    @Override
    public void run() {
        System.out.println("The length of string: " + str.length());
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new LocalExceptionHandler(null));
        thread.start();
    }
}
