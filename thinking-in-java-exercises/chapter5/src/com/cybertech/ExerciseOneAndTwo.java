package com.cybertech;

/**
 * Created by Yasholma on 26-Feb-20.
 */

class ExerciseOne {
    String name;
}

class ExerciseTwo {
    String name;
    ExerciseTwo(String name) {
        this.name = name;
    }
}

public class ExerciseOneAndTwo {
    public static void main(String[] args) {
        ExerciseOne exerciseOne = new ExerciseOne();
        System.out.println(exerciseOne.name);

        ExerciseTwo exerciseTwo = new ExerciseTwo("Cybertech");
        System.out.println(exerciseTwo.name);
    }
}
