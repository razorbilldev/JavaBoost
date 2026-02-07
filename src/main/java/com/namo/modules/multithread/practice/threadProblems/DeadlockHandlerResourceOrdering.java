package com.namo.modules.multithread.practice.threadProblems;

public class DeadlockHandlerResourceOrdering {
    static class BridgePart {
        final int id;
        final Object lock = new Object();

        BridgePart(int id) {
            this.id = id;
        }
    }

    private static final BridgePart part1 = new BridgePart(1);
    private static final BridgePart part2 = new BridgePart(2);

    public static void main(String[] args) {
        Runnable anneTask = () -> {
            crossBridge("Anne", part1, part2);
        };
        Runnable jowTask = () -> {
            crossBridge("Jow", part2, part1);
        };

        Thread.ofPlatform().start(anneTask);
        Thread.ofPlatform().start(jowTask);
    }

    private static void crossBridge(String name, BridgePart a, BridgePart b) {
        BridgePart first = a.id < b.id ? a : b;
        BridgePart second = a.id < b.id ? b : a;

        synchronized (first.lock) {
            System.out.println(name + ": Holding bridge part " + first.id);
            try {
                Thread.sleep(1000);
                synchronized (second.lock) {
                    System.out.println(name + ": Holding bridge part " + first.id + " and " + second.id);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
