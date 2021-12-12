package be.uantwerpen.fti.observers;

import be.uantwerpen.fti.GUI.ViewFrame;
import be.uantwerpen.fti.Ticket.Ticket;

import java.beans.PropertyChangeEvent;
import java.util.Objects;

public class TicketDatabaseObserver implements Observer {
    private final ViewFrame viewFrame = ViewFrame.getInstance();

    public TicketDatabaseObserver() {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        viewFrame.update_homescreen((Boolean) evt.getOldValue(), (Ticket) evt.getNewValue());
        Ticket ticket = (Ticket) evt.getNewValue();
        System.out.println(evt.getPropertyName() + " -> " + ticket);
    }
}
