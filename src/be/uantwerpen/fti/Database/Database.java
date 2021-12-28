package be.uantwerpen.fti.Database;

import be.uantwerpen.fti.Observers.Observer;

import java.beans.PropertyChangeSupport;

public abstract class Database {
    protected PropertyChangeSupport support = new PropertyChangeSupport(this);

    public Database() {
    }

    public void addObserver(Observer o) {
        support.addPropertyChangeListener(o);
    }

    public void clearObservers() {
        support = new PropertyChangeSupport(this);
    }

}
