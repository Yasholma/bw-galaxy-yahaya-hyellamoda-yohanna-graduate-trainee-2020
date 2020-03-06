package com.company;

class Music {
    class RNB {
        private String artist = "Davido";
    }

    void getArtistName() {
        RNB rnb = new RNB();
        System.out.println(rnb.artist);
    }
}

public class ExerciseEight {
    public static void main(String[] args) {
        Music music = new Music();
        music.getArtistName();
    }
}
