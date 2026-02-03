package com.namo.modules.multithread;

public class SampleThread extends Thread {
    @Override
    public void run() {
        System.out.println("The thread name: " + Thread.currentThread().getName());
        int div = 10 / 0;
        System.out.println("Div: " + div);
    }
}
