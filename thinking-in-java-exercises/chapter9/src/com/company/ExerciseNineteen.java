package com.company;

import java.util.Random;

interface Tosser {
    void toss();
}

interface TosserFactory {
    Tosser getTosser();
}

class CoinTosser implements Tosser {
    @Override
    public void toss() {
        Random rand = new Random(43);
        if (rand.nextInt(1) == 1) System.out.println("Coin tossed outcome: Head");
        else System.out.println("Coin tossed outcome: Tail");
    }
}

class CoinTosserFactory implements TosserFactory {
    @Override
    public Tosser getTosser() {
        return new CoinTosser();
    }
}

class DiceTosser implements Tosser {
    @Override
    public void toss() {
        Random rand = new Random(50);
        System.out.println("Dice tossed outcome: " + (rand.nextInt(5) + 1));
    }
}

class DiceTosserFactory implements TosserFactory {
    @Override
    public Tosser getTosser() {
        return new DiceTosser();
    }
}

public class ExerciseNineteen {
    private static void getTosster(TosserFactory factory) {
        Tosser tosser = factory.getTosser();
        tosser.toss();
    }
    public static void main(String[] args) {
        getTosster(new CoinTosserFactory());
        getTosster(new DiceTosserFactory());
    }
}
