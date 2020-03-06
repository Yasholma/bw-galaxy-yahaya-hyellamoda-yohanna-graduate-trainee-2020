package com.company;

class Car {
    private String model;
    Car (String model) {
        this.model = model;
        System.out.println("Car: " + model);
    }

    public String toString() {
        return this.model + " is the model for this Car";
    }
}

class Bike {
    private Car toyota;
    void print () {
        toyota = new Car("Camry");
        System.out.println("Inside Bike: " + toyota.toString());
    }
}

public class ExerciseOne {
    public static void main(String[] args) {
        Bike bike = new Bike();
        bike.print();
    }
}
