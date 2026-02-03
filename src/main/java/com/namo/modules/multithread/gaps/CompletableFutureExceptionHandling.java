package com.namo.modules.multithread.gaps;

import java.util.concurrent.*;

public class CompletableFutureExceptionHandling {
    private static final ExecutorService executor = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws Exception {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
                    if (true) throw new IllegalStateException("Database is down!");
                    return "Success";
                }, executor)
                .exceptionally(ex -> {
                    System.out.println("exceptionally: " + ex.getMessage());
                    return "Fallback result";
                });

        System.out.println("future1 -> " + future1.get());

        CompletableFuture<Object> future2 = CompletableFuture.supplyAsync(() -> {
                    throw new RuntimeException("Payment gateway timeout");
                }, executor)
                .handle((result, e) -> {
                    if (e != null) {
                        System.out.println("handle caught: " + e.getMessage());
                        return "Payment timeout";
                    }
                    return result;
                });
        System.out.println("future2 -> " + future2.get());

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() ->
                {
                    if (Math.random() > 0.3) throw new RuntimeException("Random failure");
                    return "Data saved";
                }, executor)
                .whenComplete((res, err) -> {
                    if (err != null) {
                        System.out.println("whenComplete -> failed: " + err);
                    } else {
                        System.out.println("whenComplete -> success: " + res);
                    }
                });

        // Still propagates exception
        // future3.get();   // ← will throw ExecutionException

        CompletableFuture<String> robustChain = CompletableFuture.supplyAsync(() -> {
                    simulateExternalService();
                    return "Order-123";
                }, executor)
                .thenApply(orderId -> {
                    return "Processed-" + orderId;
                })
                .exceptionally(ex -> {
                    System.out.println("Critical error: " + ex);
                    return "ORDER-FAILED-GIVE-DEFAULT";
                })
                .thenApply(result -> {
                    System.out.println("Final step: " + result);
                    return result.toUpperCase();
                });

        System.out.println("robustChain result = " + robustChain.get());

        Future<?> badFuture = executor.submit(() -> {
            throw new RuntimeException("Thread pool task failed");
        });

        try {
            badFuture.get();
        } catch (ExecutionException e) {
            Throwable realCause = e.getCause();
            System.out.println("Real cause was: " + realCause);
        }

        executor.shutdown();
    }

    private static void simulateExternalService() {
        if (true) throw new RuntimeException("Inventory service unavailable");
    }
}
