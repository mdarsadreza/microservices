package com.employee.utils;


interface A {
    default void show() {
        System.out.println("A's show method");
    }
}

interface B extends A {
    default void show() {
        System.out.println("B's show method");
    }
}

interface C extends A {
    default void show() {
        A.super.show();
        System.out.println("C's show method");
    }
}

class D implements B, C {
    // Resolving the ambiguity of show method
    @Override
    public void show() {
        C.super.show();
    }
}

public class DiamondProblemUsingDefault {
    public static void main(String[] args) {
        D d = new D();
        d.show();  // Output will be "C's show method"
    }
}
