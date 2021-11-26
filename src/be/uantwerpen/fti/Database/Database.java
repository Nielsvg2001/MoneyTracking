package be.uantwerpen.fti.Database;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class Database {

    protected PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public Database() {
    }

    public void addObserver(PropertyChangeListener observer) {
        this.pcs.addPropertyChangeListener(observer);
    }

    public void clearObservers() {
        this.pcs = new PropertyChangeSupport(this);
    }
    // https://www.java67.com/2013/02/10-examples-of-hashmap-in-java-programming-tutorial.html

}
