package be.uantwerpen.fti.observers;

import be.uantwerpen.fti.GUI.ViewFrame;

import java.beans.PropertyChangeEvent;

public class PersonDatabaseObserver implements Observer {

    private final ViewFrame viewFrame = ViewFrame.getInstance();

    public PersonDatabaseObserver() {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        viewFrame.update_personscreen();
        System.out.println(evt.getPropertyName() + " " + evt.getOldValue());
    }
}
