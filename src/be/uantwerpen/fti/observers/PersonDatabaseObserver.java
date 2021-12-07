package be.uantwerpen.fti.observers;

import java.beans.PropertyChangeEvent;

public class PersonDatabaseObserver implements Observer {
    public PersonDatabaseObserver() {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(evt.getPropertyName() + " " + evt.getOldValue());
    }
}
