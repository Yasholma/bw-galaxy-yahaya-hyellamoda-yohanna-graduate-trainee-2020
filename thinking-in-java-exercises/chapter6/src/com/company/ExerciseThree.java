package com.company;

import com.company.debug.Environment;

import static com.company.ExerciseThree.envnd.DEV;
import static com.company.ExerciseThree.envnd.PROD;
import static com.company.debug.Environment.debug;
import static com.company.debugof.Environment.debug;

public class ExerciseThree {
    private static Object env;

    enum envnd {
        DEV,
        PROD
    }
    public static void main(String[] args) {
        if (envnd.DEV.equals(env)) {
            Environment.debug();

        } else if (envnd.PROD.equals(env)) {
            com.company.debugof.Environment.debug();
        }
    }
}
