package com.company.exercise6Class;

import com.company.exerciseSixInterface.ExerciseSixInterface;

public class ExerciseSix {
    protected class InnerClassSix implements ExerciseSixInterface {
        @Override
        public void download() {
            System.out.println("Downloading the software in 3 2 1...");
        }
    }
}
