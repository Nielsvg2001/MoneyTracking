package be.uantwerpen.fti.Observers;

import be.uantwerpen.fti.GUI.ViewFrame;
import be.uantwerpen.fti.Ticket.Ticket;

import java.beans.PropertyChangeEvent;

public class TicketDatabaseObserver implements Observer {

    public TicketDatabaseObserver() {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ViewFrame.getInstance().update_homescreen((Boolean) evt.getOldValue(), (Ticket) evt.getNewValue());
        ViewFrame.getInstance().calculate();
        System.out.println(evt.getPropertyName() + " : " + evt.getNewValue());
    }
}
