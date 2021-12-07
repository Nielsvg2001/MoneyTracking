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
        this.support.firePropertyChange("new ticket", 0, ticket);
    }


    public void removeEntry(Ticket ticket) {
        this.ticketDatabase.remove(ticket.getUuid());
        this.support.firePropertyChange("ticket removed", 0, ticket);
    }


    public Ticket getEntry(UUID uuid) {
        return this.ticketDatabase.get(uuid);
    }

    public ArrayList<Ticket> ticketList() {
        ArrayList<Ticket> ticketList = new ArrayList<>();
        for (UUID key : ticketDatabase.keySet()) {
            ticketList.add(ticketDatabase.get(key));
        }
        return ticketList;
    }
}
