package com.cybertech;

/**
 * Created by Yasholma on 26-Feb-20.
 */
public class ExerciseTwoAndThree {
    private static void generateAndPrint() {
        int firstNum = (int) ((Math.random() + 1) * 10);
        int secondNum = (int) ((Math.random()+ 1) * 10);

        if (firstNum > secondNum) {
            System.out.println(firstNum + " is greater than " + secondNum);
        } else if (firstNum < secondNum) {
            System.out.println(firstNum + " is less than " + secondNum);
        } else {
            System.out.println(firstNum + " is equal " + secondNum);
        }
    }
    public static void exercise2() {
        for (int i = 0; i < 25; i++) {
            generateAndPrint();
        }
    }

    public static void exercise3() {
        while (true) {
            generateAndPrint();
        }
    }

    public static void main(String[] args) {
        exercise2();
        exercise3();
    }
}
