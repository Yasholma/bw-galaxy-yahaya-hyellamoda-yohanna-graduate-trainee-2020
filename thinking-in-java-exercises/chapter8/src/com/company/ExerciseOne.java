package com.company;

class Circle {
    Circle() {
        System.out.println("Circle");
    }
    void print() {
        System.out.println(this.toString());
    }
}

class Unicycle extends Circle {
    Unicycle() {
        System.out.println("Unicycle");
    }
}

class Bicycle extends Circle {
    Bicycle() {
        System.out.println("Bicycle");
    }
}

class Tricycle extends Circle {
    Tricycle() {
        System.out.println("Tricycle");
    }
}

class Tester {
    void ride(Circle circle) {
        circle.print();
    }
}

public class ExerciseOne {
    public static void main(String[] args) {
        Unicycle unicycle = new Unicycle();
        Bicycle bicycle = new Bicycle();
        Tricycle tricycle = new Tricycle();

        Tester tester = new Tester();

        tester.ride(unicycle);
        tester.ride(bicycle);
        tester.ride(tricycle);

    }
}
