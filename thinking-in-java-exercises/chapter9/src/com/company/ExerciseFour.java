package com.company;

abstract class Building {
    abstract void type(Building building);
}

class House extends Building {
    @Override
     void type(Building building) {
        ((House) building).sayHello();
    }

    private void sayHello() {
        System.out.println("Hello");
    }
}

public class ExerciseFour {
    public static void main(String[] args) {
        House house = new House();
        house.type(house);
    }
}
