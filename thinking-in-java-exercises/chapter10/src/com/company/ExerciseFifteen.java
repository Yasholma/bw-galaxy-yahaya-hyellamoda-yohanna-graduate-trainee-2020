package com.company;

class First {
    First(String name) {
        System.out.println("Hello world from First Class " + name);
    }
}

class Second {
    Second() {
        System.out.println("Second Class");
    }

    First first(final String check) {
        return new First(check) {
            private String name;
            {
                System.out.println("From the Anonymous Class");
                this.name = check;
            }
        };
    }
}

public class ExerciseFifteen {
    public static void main(String[] args) {
        Second second = new Second();
        second.first("Cybertech");
    }
}
