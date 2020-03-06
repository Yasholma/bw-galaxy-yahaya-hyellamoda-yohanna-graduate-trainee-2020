package com.cybertech;

/**
 * Created by Yasholma on 25-Feb-20.
 */
public class ExerciseEight {
    public static int counter = 0;

    public void incrementCounter() {
        ExerciseEight.counter++;
    }

    public static void main(String[] args) {
        ExerciseEight obj1 = new ExerciseEight();
        ExerciseEight obj2 = new ExerciseEight();
        ExerciseEight obj3 = new ExerciseEight();

        System.out.println("Obj1 Counter Value: " + obj1.counter);
        obj2.incrementCounter();
        System.out.println("Obj2 Counter Value: " + obj2.counter);
        System.out.println("Obj1 Counter Value After Increment by Obj2: " + obj1.counter);
        System.out.println("Obj3 Counter Value: " + obj3.counter);
    }
}
