package com.cybertech;

/**
 * Created by Yasholma on 26-Feb-20.
 */
public class ExerciseSeven {
    public static void main(String[] args) {
        int count = 1;
        while (count <= 100) {
            System.out.println(count);
            if (count == 99)
                return;
            count++;
        }
    }
}
