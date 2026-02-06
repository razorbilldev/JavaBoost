package com.namo.modules.multithread.practice;

public class ThreadDaemonExample extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println("Hello World!");
        }
    }

    public static void main(String[] args) {
        ThreadDaemonExample daemonThread = new ThreadDaemonExample();
        daemonThread.setDaemon(true);
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("User thread");
            }
        });
        daemonThread.start();
        thread.start();
        System.out.println("Main Thread Done");
    }
}
