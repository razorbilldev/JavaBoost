package com.namo.modules.multithread.practice;

public class Test {
    public static void main(String[] args) {
        World world = new World();
        world.start();
        System.out.println("World");
    }
}
