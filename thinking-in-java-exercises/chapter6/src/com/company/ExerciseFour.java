package com.company;

import com.company.simple.SimpleMaths;

import static com.company.simple.Print.print;

class Solve extends SimpleMaths {
    void saySolve() {
        sayMaths();
        System.out.println("Solve after Maths");
    }

    void tryToAccess() {
        // accessDenied(); denied because it's not public or protected
    }
}

public class ExerciseFour {
    public static void main(String[] args) {
        Solve solve = new Solve();
        solve.saySolve();
        print(SimpleMaths.add(4, 6).toString());
    }
}
