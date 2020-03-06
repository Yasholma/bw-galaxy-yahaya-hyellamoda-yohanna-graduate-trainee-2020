package com.company;

class Outer {
    private String field;
    Outer(String field) {
        this.field = field;
    }
    public Inner initialize() {
        return new Inner();
    }
    class Inner {
        Inner() {
            System.out.println("Inner Class Initialized.");
        }

        public String getOuterField() {
            return field;
        }
    }
}

public class ExerciseOneAndThree {
    public static void main(String[] args) {
        Outer outer = new Outer("Hello World");
        Outer.Inner inner = outer.initialize();
        System.out.println(inner.getOuterField());
    }
}
