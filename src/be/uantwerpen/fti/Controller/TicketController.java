package be.uantwerpen.fti.Controller;

import be.uantwerpen.fti.Database.TicketDatabase;
import be.uantwerpen.fti.Ticket.Ticket;
import be.uantwerpen.fti.observers.TicketDatabaseObserver;

public class TicketController {
    private static TicketController single_instance = null;
    private final TicketDatabase ticketDatabase = TicketDatabase.getInstance();

    private TicketController() {

    }

    public static TicketController getInstance() {
        if (single_instance == null)
            single_instance = new TicketController() {
            };

        return single_instance;
    }

    public void addTicket(Ticket ticket) {
        ticketDatabase.addEntry(ticket);
    }

    public void removeTicket(Ticket ticket) {
        ticketDatabase.removeEntry(ticket);
    }

    public Ticket[] ticketArray() {
        return ticketDatabase.ticketArray();
    }

    public void addObserver(TicketDatabaseObserver ticketDatabaseObserver){
        ticketDatabase.addObserver(ticketDatabaseObserver);
    }
}
