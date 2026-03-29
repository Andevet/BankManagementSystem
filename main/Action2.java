package main;

import interfaces.Observer;

public class Action2 implements Observer {
    @Override
    public void update(String message) {
        System.out.println("Action2 received: " + message);
    }
}

