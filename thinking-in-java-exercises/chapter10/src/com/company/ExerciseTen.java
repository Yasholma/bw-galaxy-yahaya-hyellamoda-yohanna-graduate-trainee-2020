package com.company;

interface Study1 {
    void read();
}

class StudyClass1 {
    Study1 study(String mode) {
        mode = mode.toLowerCase();
        if (mode.equals("reading")) {
            class StudyMode1 implements Study1 {
                private String mode;
                public StudyMode1(String mode) {
                    this.mode = mode;
                }

                @Override
                public void read() {
                    System.out.println("I am currently in a " + this.mode + " mood");
                }
            }
            return new StudyMode1(mode);
        }
        return null;
    }
}

public class ExerciseTen {
    public static void main(String[] args) {
        StudyClass1 studyClass1 = new StudyClass1();
        Study1 study1 = studyClass1.study("reading");
        study1.read();

        Study1 study2 = studyClass1.study("learning");
        study2.read(); // no read method in study2
    }
}
