package main;

import interfaces.Observer;

public class Action1 implements Observer {

    @Override
    public void update(String message) {
        System.out.println("Action1 received: " + message);
    }
}

