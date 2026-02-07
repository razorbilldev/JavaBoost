package com.namo.modules.multithread.practice.threadProblems;

public class DeadlockHandlerExampleConsistentOrder {
    private static final Object narrowBridgePart1 = new Object();
    private static final Object narrowBridgePart2 = new Object();

    public static void main(String[] args) {
        Runnable anneTask = () -> {
            synchronized (narrowBridgePart1) {
                System.out.println("Anne: Holding part 1 of the bridge...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Anne: Waiting for part 2 of the bridge...");
                synchronized (narrowBridgePart2) {
                    System.out.println("Anne: Holding part 1 and part 2 of the bridge...");
                }
            }
        };

        Runnable jowTask = () -> {
            synchronized (narrowBridgePart1) {
                System.out.println("Joe: Holding part 1 of the bridge");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Joe: Waiting for the part 2 of the bridge");
                synchronized (narrowBridgePart2) {
                    System.out.println("Joe: Holding part 1 and part 2 of the bridge.");
                }
            }
        };

        Thread.ofPlatform().start(anneTask);
        Thread.ofPlatform().start(jowTask);
    }
}
