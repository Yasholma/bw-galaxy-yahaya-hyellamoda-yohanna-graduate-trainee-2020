package com.company;

interface Destination {
    String readLabel();
}

class Parcel {
    public Destination destination(String label) {
        class PDestination implements Destination {
            private String label;
            private PDestination(String label) {
                this.label = label;
            }
            @Override
            public String readLabel() {
                System.out.println(this.label);
                return this.label;
            }
        }

        return new PDestination(label);
    }
}

class Percel2 {
    class Wrapping {
        Wrapping(int x){
            System.out.println(x);
        }
    }
    public Wrapping wrapping(int x) {
        return new Wrapping(x) {

        };
    }
}

public class ExamplePrac {
    public static void main(String[] args) {
//        Parcel parcel = new Parcel();
//        parcel.destination("Hello Mulla").readLabel();

        Percel2 percel2 = new Percel2();
        percel2.wrapping(10);
    }
}
