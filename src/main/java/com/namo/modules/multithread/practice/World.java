package com.namo.modules.multithread.practice;

public class World extends Thread {
    @Override
    public void run() {
        System.out.println("Run");
    }
}
