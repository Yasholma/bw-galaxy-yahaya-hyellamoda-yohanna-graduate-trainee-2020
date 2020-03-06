package com.company;

import static java.lang.System.nanoTime;

class DoThis {
    void function() {
        System.out.println("From Outer Class");
    }
    class InnerClass {
        DoThis getOuter() {
            return DoThis.this;
        }
    }

    InnerClass innerClass() {
        return new InnerClass();
    }
}

public class Main {

    public static void main(String[] args) {
//	    DoThis doThis = new DoThis();
//        DoThis.InnerClass innerClass = doThis.innerClass();
//        innerClass.getOuter().function();

//        DoThis doThis1 = new DoThis();
//        DoThis.InnerClass dni = doThis1.new InnerClass();
//        dni.getOuter().function();

        System.out.println(nanoTime());
    }
}
