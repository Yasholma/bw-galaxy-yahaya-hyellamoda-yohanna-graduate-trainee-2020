package com.company;

class Utensil {
    Utensil() {
        System.out.println("Utensil they all are. From Utensil Class");
    }
}

class Pot extends Utensil {
    Pot() {
        System.out.println("Pot is also a cooking utensil.");
    }
}

public class ExerciseThree {
    public static void main(String[] args) {
        Pot pot = new Pot();
    }
}
