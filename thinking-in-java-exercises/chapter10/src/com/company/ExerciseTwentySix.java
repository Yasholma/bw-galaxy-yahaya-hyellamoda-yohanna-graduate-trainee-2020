package com.company;

class Sport {
    class Football {
        Football() {
            System.out.println("Football");
        }
    }
}

class Game {
    class FIFA extends Sport.Football {
        FIFA(Sport sport) {
            sport.super();
        }
    }
}

public class ExerciseTwentySix {
    public static void main(String[] args) {
        Game game = new Game();
        Game.FIFA fifa = game.new FIFA(new Sport());
    }
}
