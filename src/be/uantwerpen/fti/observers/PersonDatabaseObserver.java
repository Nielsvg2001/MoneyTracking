package be.uantwerpen.fti.observers;

import be.uantwerpen.fti.GUI.ViewFrame;
import be.uantwerpen.fti.Person;

import java.beans.PropertyChangeEvent;

public class PersonDatabaseObserver implements Observer {

    public PersonDatabaseObserver() {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ViewFrame.getInstance().update_personscreen((Boolean) evt.getOldValue(), (Person) evt.getNewValue());
        System.out.println(evt.getPropertyName() + " " + evt.getOldValue());
    }
}
