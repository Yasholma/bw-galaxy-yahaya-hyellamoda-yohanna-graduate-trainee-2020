package com.company;

import static com.company.simple.Print.*;
import static com.company.simple.SimpleMaths.*;

class Circle {
    private Circle() {}
    public static Circle instance() { return new Circle(); }
    public void hi() {
        System.out.println("Hi from mulla circle");
    }
}

public class Main {

    public static void main(String[] args) {
        print("How are you");
        print(add(3, 6) + "");

        Circle circle = Circle.instance();
        circle.hi();
    }
}
