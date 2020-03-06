package com.cybertech;

/**
 * Created by Yasholma on 26-Feb-20.
 */

public class ExerciseNine {

    public static void fibonnaci(int n) {
        int init = 1;
        int prev = 1;
        int i = 2;

        System.out.print(init + ", " + prev + ", ");
        while (i <= n) {
            int temp;
            System.out.print(i + ", ");
            temp = i;
            i += prev;
            prev = temp;
        }
    }


    public static void main(String[] args) {
        fibonnaci(10);
    }
}
