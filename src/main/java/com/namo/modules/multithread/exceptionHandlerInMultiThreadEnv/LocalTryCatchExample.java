package com.namo.modules.multithread.exceptionHandlerInMultiThreadEnv;

public class LocalTryCatchExample implements Runnable {

    @Override
    public void run() {
        try {
            //simulate task that throws exception
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Exception in thread: " + Thread.currentThread().getName() + " - " + e.getMessage());
        }
    }
}
