package com.company;
abstract class Rodent {
    private String name;

    Rodent(String name) {
        this.name = name;
    }

    abstract void sayName();
    abstract void eat();
    void bite() {
        System.out.println(this.name + " is eating.");
    }
}

class Mouse extends Rodent {
    private String name;
    Mouse(String name) {
        super(name);
        this.name = name;
    }

    @Override
    void sayName() {
        System.out.println(this.name + " is a type of Mouse");
    }

    @Override
    void eat() {}

}

class Gerbil extends Rodent {
    private String name;
    Gerbil(String name) {
        super(name);
        this.name = name;
    }

    @Override
    void sayName() {
        System.out.println(this.name + " is a type of Gerbil");
    }

    @Override
    void eat() {

    }
}

class Hamster extends Rodent {
    private String name;
    Hamster(String name) {
        super(name);
        this.name = name;
    }

    @Override
    void sayName() {
        System.out.println(this.name + " is a type of Hamster");
    }

    @Override
    void eat() {

    }
}

public class ExerciseOne {
    private static void talk(Rodent rodent) {
        rodent.sayName();
        rodent.bite();
    }
    private static void findAll(Rodent[] rodents) {
        for (Rodent rodent: rodents) {
            talk(rodent);
        }
    }

    public static void main(String[] args) {
        Rodent[] rodents = {
                new Mouse("Monks"),
                new Hamster("Joky"),
                new Gerbil("Pinsu")
        };

        findAll(rodents);
    }
}
