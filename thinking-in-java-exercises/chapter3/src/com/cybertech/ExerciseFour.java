package com.cybertech;

import java.util.Random;

/**
 * Created by Yasholma on 25-Feb-20.
 */
class Velocity {
    Random rand = new Random(100);
    float distance = rand.nextFloat() + 1;
    float time = rand.nextFloat() + 1;

    public float calculate() {
        System.out.println(distance + " " + time);
        return distance / time;
    }
}

public class ExerciseFour {
    public static void main(String[] args) {
        Velocity velocity = new Velocity();
        float res = velocity.calculate();
        System.out.println(res);
    }
}
