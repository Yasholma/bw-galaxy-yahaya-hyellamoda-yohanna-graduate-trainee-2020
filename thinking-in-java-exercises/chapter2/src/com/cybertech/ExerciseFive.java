package com.cybertech;

/**
 * Created by Yasholma on 25-Feb-20.
 */
public class ExerciseFive {
    int age;
    String name;
    double hours;

    public static void main(String[] args) {
        ExerciseFive exerciseFive = new ExerciseFive();
        exerciseFive.age = 24;
        exerciseFive.name = "John Doe";
        exerciseFive.hours = 23.4;

        System.out.println("My Name is " + exerciseFive.name + " and I am " + exerciseFive.age + " years old. I work for " + exerciseFive.hours + " hours daily.");
    }
}
