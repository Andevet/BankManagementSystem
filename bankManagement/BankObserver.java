package bankManagement;

import interfaces.Observer;

import java.util.ArrayList;
import java.util.List;

public class BankObserver {
    private List<Observer> observers = new ArrayList<>();

    // Add an observer
    public void attach(Observer o) {
        observers.add(o);
    }

    public void detach(Observer o) {
		observers.remove(o);
	}

    // Notify all observers
    public void notify(String message) {
        for (Observer obs : observers) {
            obs.update(message);
        }
    }
}
