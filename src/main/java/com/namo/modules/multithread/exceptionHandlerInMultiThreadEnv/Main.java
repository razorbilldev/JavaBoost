package com.namo.modules.multithread.exceptionHandlerInMultiThreadEnv;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
//        Thread thread = new Thread(new LocalTryCatchExample(), "WorkerThread");
//        thread.start();
//        System.out.println("Main thread continues...");

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<String> future = executor.submit(new LocalTryCatchCallable());
        try {
            String result = future.get();
            System.out.println("Got: " + result);
        } catch (ExecutionException | InterruptedException e) {
            System.err.println("Uncaught from Callable: " + e.getCause());
        }

        executor.shutdown();
    }
}
