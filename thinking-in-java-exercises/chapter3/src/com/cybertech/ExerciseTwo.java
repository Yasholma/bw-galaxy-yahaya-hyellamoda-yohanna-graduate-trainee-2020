package com.cybertech;

class Result {
    float score;
}

public class ExerciseTwo {
    public static void main(String[] args) {
	    Result john = new Result();
	    Result anna = new Result();

	    john.score = 29.5f;
	    anna.score = 25.22f;

        System.out.println("John's score: " + john.score + " and Anna's score: " + anna.score);
        System.out.println("System Check: John and Anna have same score: ");
        john = anna;
        System.out.println("New Result => John's score: " + john.score + " and Anna's score: " + anna.score);

    }
}
