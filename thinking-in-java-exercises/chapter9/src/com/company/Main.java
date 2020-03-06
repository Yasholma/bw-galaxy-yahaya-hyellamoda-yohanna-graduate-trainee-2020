package com.company;

enum Note {
    KEY_G, KEY_F, KEY_Z
}

abstract class Instrument {
    private int i;
    public abstract void play(Note i);
    public String what() {return "Instrument";}
    public abstract void adjust();
}

class Wind extends Instrument {
    @Override
    public void play(Note i) {
        System.out.println("Wind.play() " + i);
    }

    @Override
    public String what() {
        return "Wind";
    }

    @Override
    public void adjust() {

    }
}

public class Main {
    static void tune(Instrument i) {
        i.play(Note.KEY_F);
    }

    static void playAll(Instrument[] instruments) {
        for (Instrument i: instruments) {
            tune(i);
        }
    }
    public static void main(String[] args) {
	    Instrument[] instruments = {
	            new Wind()
        };
	    playAll(instruments);
    }
}
