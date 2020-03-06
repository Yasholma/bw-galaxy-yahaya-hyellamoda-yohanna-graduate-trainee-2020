package com.company.interfaceinit;

public interface Building {
    int count = 5;
    void type();
    String description(String location);
    default void printCount() {
        System.out.println(count);
    }
}
