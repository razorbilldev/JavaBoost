package com.namo.modules.multithread.gaps;
/*
Exception Handling in Multithreading: Prepare and share code examples demonstrating how to handle exceptions in threads, daemon threads,
                                      and using UncaughtExceptionHandler in Java. (Mukhammad) 1
CompletableFuture Exception Handling: Prepare and share code examples using CompletableFuture async and catching exceptions in Java. (Mukhammad) 2

ExecutionException signals that the asynchronous task encountered an exception, and you should inspect its cause to handle the underlying error

Future<?> future = executor.submit(() -> {    // Task logic    throw new RuntimeException("Error in thread");});


What will happen if you get an exception in your rest api which you have not caught
(your api does not have any try catch block and you get a runtime exception) ?
 */
public class Solution {
    public static void main(String[] args) {

    }
}
