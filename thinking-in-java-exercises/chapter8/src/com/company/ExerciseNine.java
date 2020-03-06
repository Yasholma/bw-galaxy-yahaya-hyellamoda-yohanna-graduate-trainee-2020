package com.company;

class Rodent {
    private String name;

    public Rodent(String name) {
        this.name = name;
    }

    void sayName() {
        System.out.println(name);
    }
    void eat() {}
    void bite() {}
}

class Mouse extends Rodent {
    private String name;
    Mouse(String name) {
        super(name);
        this.name = name;
    }

    @Override
    void sayName(){
        System.out.println(this.name + " is my name. I am a Mouse.");
    }
}

class Gerbil extends Rodent {
    private String name;
    Gerbil(String name) {
        super(name);
        this.name = name;
    }

    @Override
    void sayName(){
        System.out.println(this.name + " is my name. I am a Gerbil.");
    }
}

class Hamster extends Rodent {
    private String name;
    Hamster(String name) {
        super(name);
        this.name = name;
    }

    @Override
    void sayName(){
        System.out.println(this.name + " is my name. I am a Hamster.");
    }
}

public class ExerciseNine {
    private static void sayName(Rodent rodent) {
        rodent.sayName();
    }
    private static void findAll(Rodent[] rodents) {
        for (Rodent rodent: rodents) {
            sayName(rodent);
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
