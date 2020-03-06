package com.cybertech;

/**
 * Created by Yasholma on 26-Feb-20.
 */
class Tank {
    boolean filled = false;
    Tank(boolean filled) {
        this.filled = filled;
    }

    boolean empty() {
        if (this.filled){
            this.filled = false;
            System.out.println("Free");
            return true;
        }
        return false;
    }

    @Override
    protected void finalize() throws Throwable {
        if (!filled) {
            super.finalize();
        }
    }
}
public class ExerciseTwelve {
    public static void main(String[] args) {
        Tank tank = new Tank(true);
        tank.empty();

        new Tank(true);
        System.gc();
    }
}
