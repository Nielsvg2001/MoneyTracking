package be.uantwerpen.fti.Database;

import be.uantwerpen.fti.Ticket.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class TicketDatabase extends Database {
    private static TicketDatabase single_instance = null;
    private final HashMap<UUID, Ticket> ticketDatabase;

    private TicketDatabase() {
        this.ticketDatabase = new HashMap<>();
    }

    public static TicketDatabase getInstance() {
        if (single_instance == null)
            single_instance = new TicketDatabase() {
            };

        return single_instance;
    }

    public void addEntry(Ticket ticket) {
        this.ticketDatabase.put(ticket.getUuid(), ticket);
        this.support.firePropertyChange("new ticket", true, ticket);
    }


    public void removeEntry(Ticket ticket) {
        this.ticketDatabase.remove(ticket.getUuid());
        this.support.firePropertyChange("ticket removed", false, ticket);
    }


    public Ticket getEntry(UUID uuid) {
        return this.ticketDatabase.get(uuid);
    }

    public Ticket[] ticketArray() {
        ArrayList<Ticket> ticketArrayList = new ArrayList<>();
        for (UUID key : ticketDatabase.keySet()) {
            ticketArrayList.add(ticketDatabase.get(key));
        }
        return ticketArrayList.toArray(new Ticket[0]);
    }
}
