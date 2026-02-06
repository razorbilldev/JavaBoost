package com.namo.modules.multithread;

import com.namo.modules.multithread.practice.Counter;

public class Main extends Thread {
    private Counter counter;

    public Main(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.increment();
        }
    }

    public static void main(String[] args) {
        Counter counter_one = new Counter();
        Main thread_one = new Main(counter_one);
        Main thread_two = new Main(counter_one);
        thread_one.start();
        thread_two.start();
        try {
            thread_one.join();
            thread_two.join();
        } catch (Exception e) {

        }
        System.out.println(counter_one.getCount());
    }
}
