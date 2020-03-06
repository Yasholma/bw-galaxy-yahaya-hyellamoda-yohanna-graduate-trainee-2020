package com.company;

class Super {
    public int field = 0;
}

class Sub extends Super {
    public int field = 1;
}
class Useful {
    public void f() {}
    public void g() {}
}

class MoreUseful extends Useful {
    public void u() {
        System.out.println("Hi");
    }
}

public class Main {

    public static void main(String[] args) {
        Super s = new Sub();
        System.out.println(s.field);
        System.out.println(((Sub) s).field);

        Useful[] x = {
                new Useful(),
                new MoreUseful()
        };
        x[0].f();
        x[1].g();
//        x[1].u();
        ((MoreUseful)x[1]).u();
//        ((MoreUseful)x[0]).u();
    }
}
