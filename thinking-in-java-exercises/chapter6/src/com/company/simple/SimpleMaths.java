package com.company.simple;

import java.util.Arrays;
import java.util.stream.Stream;

public class SimpleMaths {
    public static Integer add(int a, int b) {
        return a + b;
    }

    public static int subtract(int a, int b) {
        return a - b;
    }

    protected void sayMaths() {
        System.out.println("Simple Maths");
    }

    void accessDenied() {
        System.out.println("This method has package access");
    }
}
