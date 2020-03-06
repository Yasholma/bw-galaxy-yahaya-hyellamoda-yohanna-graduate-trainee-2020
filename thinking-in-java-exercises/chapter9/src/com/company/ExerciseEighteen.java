package com.company;

interface Circle {
    void draw();
}

interface CircleFactory {
    Circle getCircle();
}

class Unicycle implements Circle {
    @Override
    public void draw() {
        System.out.println("Drawing a Unicycle");
    }
}

class UnicyleFactory implements CircleFactory {
    @Override
    public Circle getCircle() {
        return new Unicycle();
    }
}

class Bicycle implements Circle {
    @Override
    public void draw() {
        System.out.println("Drawing a Bicycle");
    }
}

class BicycleFactory implements CircleFactory {
    @Override
    public Circle getCircle() {
        return new Bicycle();
    }
}

class Tricycle implements Circle {
    @Override
    public void draw() {
        System.out.println("Drawing a Tricycle");
    }
}

class TricycleFactory implements CircleFactory {
    @Override
    public Circle getCircle() {
        return new Tricycle();
    }
}

public class ExerciseEighteen {
    public static void drawCircle(CircleFactory factory) {
        Circle circle = factory.getCircle();
        circle.draw();
    }
    public static void main(String[] args) {
        drawCircle(new UnicyleFactory());
        drawCircle(new BicycleFactory());
        drawCircle(new TricycleFactory());
    }
}
