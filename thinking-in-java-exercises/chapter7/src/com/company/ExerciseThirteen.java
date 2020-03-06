package com.company;

class Base {
    void sample(String name) {
        System.out.println("String sample: " + name);
    }

    void sample(String name, int size) {
        System.out.println("String same with int: " + name + " " + size);
    }

    void sample(float length) {
        System.out.println("Float sample: " + length);
    }
}

class Child extends Base {
    void sample(int age) {
        System.out.println("Sample inside child's class: " + age);
    }
}

public class ExerciseThirteen {
    public static void main(String[] args) {
        Child child = new Child();
        child.sample(1);
        child.sample(23f);
        child.sample("Cyber");
        child.sample("Mario", 33);
    }
}
