package com.company.interfaceimpl;

import com.company.interfaceinit.Building;

import java.util.Arrays;

class House implements Building {
    private final String type = "House";

    @Override
    public void type() {
        System.out.println("Type of House");
    }

    @Override
    public String description(String location) {
        return "This is a type of " + this.type +  " located at " + location;
    }

    public String name() {
        return getClass().getSimpleName();
    }
}

public class ExerciseFiveAndSix {
    public static void main(String[] args) {
        House house  = new House();
        house.type();
        System.out.println(house.name());
//        house.count++; // prove for static and final
        System.out.print("Count: "); house.printCount();
        System.out.println(house.description("Maitama"));
        String name = "cyber";

        System.out.println(Arrays.toString(name.split("")));
    }
}
