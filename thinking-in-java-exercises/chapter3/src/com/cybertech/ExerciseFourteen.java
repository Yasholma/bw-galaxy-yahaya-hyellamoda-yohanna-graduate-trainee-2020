package com.cybertech;

/**
 * Created by Yasholma on 25-Feb-20.
 */

class CustomString {
    boolean strcmp(String str1, String str2) {
        if (str1.equals(str2)) {
            return true;
        } else if (str1 == str2) {
            return true;
        } else if (str1 != str2) {
            return false;
        }
        return false;
    }
}

public class ExerciseFourteen {
    public static void main(String[] args) {
        CustomString customString = new CustomString();
        String s1 = "hello";
        String s2 = "world";
        String s3 = "hello";

        String ob1 = new String("hello");
        String ob2 = new String("world");

        boolean s1Ands2 = customString.strcmp(s1, s2);
        System.out.println("Comparisons btw s1 and s2: " + s1Ands2);

        boolean s1Ands3 = customString.strcmp(s1, s3);
        System.out.println("Comparisons btw s1 and s3: " + s1Ands3);

        boolean obj1Andobj2 = customString.strcmp(ob1, ob2);
        System.out.println("Comparisons btw obj1 and obj2: " + obj1Andobj2);
    }
}
