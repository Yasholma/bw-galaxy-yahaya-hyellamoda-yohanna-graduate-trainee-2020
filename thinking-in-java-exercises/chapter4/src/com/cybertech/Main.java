package com.cybertech;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static boolean condition() {
        boolean result = Math.random() < 0.99;
        System.out.println(result + ", ");
        return result;
    }

    static List<Integer> range(int n) {
        List numbers = new ArrayList();
        for (int i = 0; i < n; i++)
            numbers.add(i);
        return numbers;
    }

    public static void main(String[] args) {

        for (int i : range(10))
            System.out.print(i + " ");

//	    while (condition()) {
//            System.out.println("Inside Loop");
//        }
//        System.out.println("Exited");
//        for (char c = 0; c < 128; c++) {
//            if (Character.isLowerCase(c) || Character.isUpperCase(c)) {
//                System.out.println("Value: " + (int) c + " character: " + c);
//            }
//        }


//        String s = "cybertech";
//        for (char c : s.toCharArray()) {
//            System.out.println((int) c);
//        }

    }
}
