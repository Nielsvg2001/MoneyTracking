package be.uantwerpen.fti.observers;

import be.uantwerpen.fti.Ticket.Ticket;

import java.beans.PropertyChangeEvent;

public class TicketDatabaseObserver implements Observer {

    public TicketDatabaseObserver() {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Ticket ticket = (Ticket) evt.getNewValue();
        System.out.println(evt.getPropertyName() + " -> " + ticket);
    }
}
