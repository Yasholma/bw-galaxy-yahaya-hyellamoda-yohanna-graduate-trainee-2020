package com.company;

class DetergentDelegation {
    private Cleanser cleanser;

    public DetergentDelegation() {
        this.cleanser = new Cleanser();
        cleanser.toString();
    }

    public void append(String a) {
        cleanser.append(a);
    }

    public void dilute() {
        cleanser.dilute();
    }

    public void apply() {
        cleanser.apply();
    }

    public void scrub() {
        cleanser.scrub();
    }
}

public class ExerciseEleven {
    public static void main(String[] args) {
        DetergentDelegation delegation = new DetergentDelegation();
        delegation.scrub();
    }
}
