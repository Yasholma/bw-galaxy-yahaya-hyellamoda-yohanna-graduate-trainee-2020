package com.company;

interface Cycle { void draw(); }
interface CycleFactory {Cycle cycle();}

class Unicycle implements Cycle {
    @Override
    public void draw() {
        System.out.println("Drawing a Unicycle");
    }

    public static CycleFactory factory = new CycleFactory() {
        @Override
        public Cycle cycle() {
            return new Unicycle();
        }
    };
}

class Bicycle implements Cycle {

    @Override
    public void draw() {
        System.out.println("Drawing a Bicycle");
    }

    public static CycleFactory factory = new CycleFactory() {
        @Override
        public Cycle cycle() {
            return new Bicycle();
        }
    };
}

class Tricycle implements Cycle {

    @Override
    public void draw() {
        System.out.println("Drawing a Tricycle");
    }

    public static CycleFactory factory = new CycleFactory() {
        @Override
        public Cycle cycle() {
            return new Tricycle();
        }
    };
}

public class ExerciseSixteen {
    private static void cycleConsume(CycleFactory factory) {
        Cycle cycle = factory.cycle();
        cycle.draw();
    }

    public static void main(String[] args) {
        cycleConsume(Unicycle.factory);
        cycleConsume(Bicycle.factory);
        cycleConsume(Tricycle.factory);
    }
}
