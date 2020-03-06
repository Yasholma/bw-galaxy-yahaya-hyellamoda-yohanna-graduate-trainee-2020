package com.cybertech;

/**
 * Created by Yasholma on 26-Feb-20.
 */
class Dog {
    void bark() {
        System.out.println("Barking...");
    }

    void bark(String sound) {
        System.out.println(sound);
    }

    void bark(int times) {
        System.out.println("Barking for " + times + " times.");
    }

    void bark(String who, int times) {
        System.out.println(who + " is barking " + times + " times.");
    }
}
public class ExerciseFive {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.bark();
        dog.bark(2);
        dog.bark("Puppy", 4);
        dog.bark("Worf!");
    }
}
