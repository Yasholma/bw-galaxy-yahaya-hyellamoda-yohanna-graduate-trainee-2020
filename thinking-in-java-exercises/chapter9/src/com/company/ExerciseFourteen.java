package com.company;

interface Human {
    void name();
    void age();
}

interface Device {
    void deviceName();
    void deviceCount();
}

interface Car {
    void model();
    void number();
}

interface PersonPrototype extends Human, Device, Car {
    String info();
}

class DetailsGenerator {
    public void f(Human human) {
        human.name();
    }

    public void l(Human human) {
        human.age();
    }

    public void g(Car car) {
        car.model();
    }

    public void h(Device device) {
        device.deviceName();
    }
}

class Person extends DetailsGenerator implements PersonPrototype {
    private String name;
    private int age;
    private String deviceName;
    private String carModel;

    public Person(String name, int age, String deviceName, String carModel) {
        this.name = name;
        this.age = age;
        this.deviceName = deviceName;
        this.carModel = carModel;
    }

    @Override
    public void name() {
        System.out.println(this.name);
    }

    @Override
    public void age() {
        System.out.println(this.age);
    }

    @Override
    public void deviceName() {
        System.out.println(this.deviceName);
    }

    @Override
    public void deviceCount() {

    }

    @Override
    public void model() {
        System.out.println(this.carModel);
    }

    @Override
    public void number() {

    }

    @Override
    public String info() {
        return null;
    }
}

public class ExerciseFourteen {
    public static void main(String[] args) {
        Human john = new Person("John", 12, "Nokia", "Honda");

        john.name();
        john.age();
        ((Person) john).deviceName();
    }
}
