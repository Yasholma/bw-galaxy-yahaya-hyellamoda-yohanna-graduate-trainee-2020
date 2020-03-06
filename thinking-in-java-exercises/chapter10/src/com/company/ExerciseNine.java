package com.company;

interface Study {
    void read();
}

class StudyClass {
    Study study(String mode) {
        class StudyMode implements Study {
            private String mode;
            public StudyMode(String mode) {
                this.mode = mode;
            }

            @Override
            public void read() {
                System.out.println("I am currently in a " + this.mode + " mood");
            }
        }

        return new StudyMode(mode);
    }
}

public class ExerciseNine {
    public static void main(String[] args) {
        StudyClass studyClass = new StudyClass();
        Study study = studyClass.study("Reading");
        study.read();
    }
}
