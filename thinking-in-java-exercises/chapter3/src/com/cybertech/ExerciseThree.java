package com.cybertech;

import java.util.Random;

/**
 * Created by Yasholma on 25-Feb-20.
 */

class Count {
    float v;
}

public class ExerciseThree {
    static void set(Count c, float newV) {
        c.v = newV;
    }

    public static void main(String[] args) {
        Count c1 = new Count();
        c1.v = 50.22f;
        System.out.println("Count Initial: " + c1.v);

        // Calling Setter
        set(c1, 200f);
        System.out.println("Count After Setter: " + c1.v);
    }
}
