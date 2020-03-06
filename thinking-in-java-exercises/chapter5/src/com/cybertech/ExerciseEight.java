package com.cybertech;

/**
 * Created by Yasholma on 26-Feb-20.
 */
class Human {
    void eat() {
        sleep();
        this.sleep();
    }

    void sleep() {
        System.out.println("Sleeping");
    }
}
public class ExerciseEight {
    public static void main(String[] args) {
        Human human = new Human();
        human.eat();
    }
}
