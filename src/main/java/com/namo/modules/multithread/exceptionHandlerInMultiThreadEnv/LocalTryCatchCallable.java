package com.namo.modules.multithread.exceptionHandlerInMultiThreadEnv;

import java.util.concurrent.Callable;

public class LocalTryCatchCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        try {
            //simulate work that throws exception
            int result = 10 / 0;
            return "Success: " + result;
        } catch (ArithmeticException e) {
            System.out.println("Caught in Callable: " +
                    Thread.currentThread().getName() + " -> " + e.getMessage());
            return "Failed (handled locally)";
        }
    }
}
