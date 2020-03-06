package com.cybertech;

/**
 * Created by Yasholma on 25-Feb-20.
 */
public class ExerciseTen {
    public static void main(String[] args) {
        int first = 0x2;
        int second = 0xa;

        int and = first & second;
        System.out.println("Bitwise AND operator: " + Integer.toBinaryString(and));

        int or = first | second;
        System.out.println("Bitwise OR operator: " + Integer.toBinaryString(or));

        int xor = first ^ second;
        System.out.println("Bitwise XOR operator: " + Integer.toBinaryString(xor));

        int not = ~second;
        System.out.println("Bitwise NOT operator: " + Integer.toBinaryString(not));
    }
}
