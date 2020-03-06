package com.company;

interface U {
    void eat();
    void code();
    void sleep();
}

class A {
    U makeU() {
        return new U() {
            @Override
            public void eat() {
                System.out.println("I am eating Inside Class A");
            }

            @Override
            public void code() {
                System.out.println("I am coding Inside Class A");
            }

            @Override
            public void sleep() {
                System.out.println("I am sleeping Inside Class A");
            }
        };
    }
}

class B {
    public U[] demUs = new U[10];
     void collectU(U u, int pos) {
        demUs[pos] = u;
    }

    public void setU(int pos) {
        demUs[pos] = null;
    }

    public void moveU(U[] find) {
        for (int i = 0; i < demUs.length; i++) {
            if (find[i] != null) {
                find[i].sleep();
                find[i].code();
                find[i].eat();
            }
        }
    }
}


public class ExerciseTwentyThree {
    public static void main(String[] args) {
        A[] arefs = new A[10];
        for (int i = 0; i < 10; i++) {
            arefs[i] = new A();
        }

        B b = new B();
        for (int i = 0; i < 10; i++) {
            b.collectU(arefs[i].makeU(), i);
        }

        b.moveU(b.demUs);
    }

}
