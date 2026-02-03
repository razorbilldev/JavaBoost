package com.namo.modules.gaps.classes;

public class Test {
    public static void main(String[] args) {
        Test test1 = new Test();
        test1.method1(null);

    }

    public void method1(Object o) {
        System.out.println("Object call");
    }

    public void method1(String o) {
        System.out.println("String call");
    }

//    public void method1(Integer o) {
//        System.out.println("Integer call");
//    }
}
