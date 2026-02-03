package com.namo.modules.gaps.classes;

public class Q {
    static class A extends B {
        static void staticMethod() {
            System.out.println("Static method from A");
        }

        void nonStaticMethod() {
            System.out.println("Non-static method from A");
        }
    }

    static class B {
        static void staticMethod() {
            System.out.println("Static method from B");
        }

        void nonStaticMethod() {
            System.out.println("Non-static method from B");
        }
    }

    public static void main(String[] args) {
        A a = new A();
        B b = new B();

        a.staticMethod();
        b.staticMethod();

        B aORb = a;
        aORb.staticMethod();
        aORb.nonStaticMethod();
    }
}
