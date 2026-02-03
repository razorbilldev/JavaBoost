package com.namo.modules.multithread.exceptionHandlerInMultiThreadEnv;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableBasic {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CompletableFuture<?> future = CompletableFuture.supplyAsync(() -> {
                    if (true) throw new RuntimeException("Task Crashed");
                    System.out.println("Never printed");
                    return null;
                }, executorService).thenRun(() -> System.out.println("Never reached"))
                .exceptionally(ex -> {
                    System.err.println("Handled: " + ex.getMessage());
                    return null;
                });

        System.out.println("Main continues");
        future.join();
    }
}
