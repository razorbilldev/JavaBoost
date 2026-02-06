package com.namo.modules.multithread.practice;

public class WorldRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("Runnable task");
    }
}
