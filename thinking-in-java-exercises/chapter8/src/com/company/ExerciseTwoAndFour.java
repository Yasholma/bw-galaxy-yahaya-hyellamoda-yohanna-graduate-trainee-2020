package com.company;

import java.util.Random;

class Shape {
    void draw() {
    }
}

class Square extends Shape {
    @Override
    void draw() {
        System.out.println("Square.draw()");
    }
}

class Rectangle extends Shape {
    @Override
    void draw() {
        System.out.println("Rectangle.draw()");
    }
}

class Triangle extends Shape {
    @Override
    void draw() {
        System.out.println("Triangle.draw()");
    }
}

class RandomGen {
    private Random random = new Random(47);
    Shape next() {
        switch (random.nextInt(3)) {
            default:
            case 0: return new Rectangle();
            case 1: return new Square();
            case 2: return new Triangle();
        }
    }
}

public class ExerciseTwoAndFour {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[9];
        RandomGen randomGen = new RandomGen();
        for (int i = 0; i < shapes.length; i++) {
            shapes[i] = randomGen.next();
        }

        for (Shape s: shapes) {
            s.draw();
        }
    }
}
