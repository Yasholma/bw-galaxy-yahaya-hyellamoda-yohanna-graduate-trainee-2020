package com.cybertech;

public class ExerciseTwentyOne {
    public static void main(String[] args) {
        for (Currency c : Currency.values()) {
            System.out.println(c + ": " + c.ordinal());
        }
    }

    /**
     * Created by Yasholma on 26-Feb-20.
     */

    public static enum Currency {
        NAIRA, DOLLAR, POUND, YEN, EURO, CEDI
    }
}
