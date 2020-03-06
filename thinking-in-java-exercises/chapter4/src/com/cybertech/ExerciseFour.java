package com.cybertech;

/**
 * Created by Yasholma on 26-Feb-20.
 */
public class ExerciseFour {
    private static void primeNumber() {
        for (int i = 2; i <= 20; i++) {
            int c = 0;
            for (int j = 1; j <= 20; j++) {
                if (i % j == 0 && i % 1 == 0) {
                    c++;
                }
            }
            if (c == 2)
                System.out.println("Prime Numbers from: " + i);
        }
    }
    public static void main(String[] args) {
        primeNumber();
    }

}
