package com.cybertech;

/**
 * Created by Yasholma on 25-Feb-20.
 */
class Dog {
    String name, says;

    // Just Practicing
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Dog) {
            if (this.name == ((Dog) obj).name) {
                return true;
            }
            return false;
        }
        return false;
    }
}

public class ExerciseFiveAndSix {
    public static void main(String[] args) {
        Dog spot = new Dog();
        spot.name = "spot";
        spot.says = "Ruff!";

        Dog spuffy = new Dog();
        spuffy.name = "Spuffy";
        spuffy.says = "Wurf!";

        System.out.println(spot.name + " is saying " + spot.says);
        System.out.println(spuffy.name + " is saying " + spuffy.says);

        System.out.println(spot == spuffy);
        System.out.println(spot.equals(spuffy));

        // Exercise 8
        int hex = 0xa;
        String n = Long.toBinaryString(hex);
        System.out.println(n);

        double exp = 10e3;
        System.out.println(exp);

    }
}

