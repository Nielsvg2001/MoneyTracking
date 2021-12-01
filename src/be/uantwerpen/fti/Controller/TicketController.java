package be.uantwerpen.fti.Controller;

import be.uantwerpen.fti.Database.TicketDatabase;
import be.uantwerpen.fti.Person;
import be.uantwerpen.fti.Ticket.Ticket;

import java.util.ArrayList;

public class TicketController {
    private static TicketController single_instance = null;
    private final TicketDatabase ticketDatabase;

    private TicketController(TicketDatabase ticketDatabase) {
        this.ticketDatabase = ticketDatabase;
    }

    public static TicketController getInstance(TicketDatabase ticketDatabase) {
        if (single_instance == null)
            single_instance = new TicketController(ticketDatabase) {
            };

        return single_instance;
    }

    public void addTicket(Ticket ticket) {
        ticketDatabase.addEntry(ticket);
    }

    public void removeTicket(Ticket ticket) {
        ticketDatabase.removeEntry(ticket);
    }

    public ArrayList<Ticket> ticketList() {
        return ticketDatabase.ticketList();
    }

    public void split(Ticket ticket){
        for(Person person: ticket.getOwsList().keySet()){
            person.addDept(ticket.getPayer(), -ticket.getOwsList().get(person));
            ticket.getPayer().addDept(person, ticket.getOwsList().get(person));
        }
    }
}
