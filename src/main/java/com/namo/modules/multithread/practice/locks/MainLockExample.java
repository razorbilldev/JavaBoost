package com.namo.modules.multithread.practice.locks;

public class MainLockExample {
    public static void main(String[] args) {
        BankAccount sbi = new BankAccount();
        Runnable task = () -> {
            sbi.withdraw(20);
        };
        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        t1.start();
        t2.start();
    }
}
