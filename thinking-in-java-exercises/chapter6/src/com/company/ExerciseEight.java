package com.company;

class ConnectionManager {
    private static int counter = 0;
    private ConnectionManager () {}
    public static ConnectionManager instance() {
        if (ConnectionManager.counter == 4) {
            return null;
        }
        ConnectionManager.counter++;
        return new ConnectionManager();
    }

    void status() {
        System.out.println("Av");
    }
}

public class ExerciseEight {
    public static void main(String[] args) {
        ConnectionManager connectionManager = ConnectionManager.instance();
        connectionManager.status();
        ConnectionManager connectionManager1 = ConnectionManager.instance();
        ConnectionManager connectionManager2 = ConnectionManager.instance();
        ConnectionManager connectionManager3 = ConnectionManager.instance();

        connectionManager3.status();
        ConnectionManager connectionManager4 = ConnectionManager.instance();
        connectionManager4.status();
    }
}
