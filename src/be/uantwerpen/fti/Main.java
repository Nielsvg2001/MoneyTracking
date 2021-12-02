package be.uantwerpen.fti;

import be.uantwerpen.fti.Controller.PersonController;
import be.uantwerpen.fti.Controller.TicketController;
import be.uantwerpen.fti.Database.PersonDatabase;
import be.uantwerpen.fti.Database.TicketDatabase;
import be.uantwerpen.fti.Factory.TicketFactory;
import be.uantwerpen.fti.Ticket.Ticket;
import be.uantwerpen.fti.Ticket.TicketType;

import java.util.HashMap;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        // Init
        TicketFactory ticketFactory = new TicketFactory();
        TicketDatabase ticketDatabase = TicketDatabase.getInstance();
        PersonDatabase personDatabase = PersonDatabase.getInstance();
        TicketController ticketController = TicketController.getInstance(ticketDatabase);
        PersonController personController = PersonController.getInstance(personDatabase);

        Person niels = new Person("Niels", "niels@uantwerpen.be", "0453503949");
        Person thijs = new Person("Thijs", "thijs@uantwerpen.be", "0487529926");
        Person maxim = new Person("Maxim", "maxim@uantwerpen.be", "0485930030");

        personController.addPerson(niels);
        personController.addPerson(thijs);
        personController.addPerson(maxim);

        // Ticket 1
        Ticket ticket = ticketFactory.getTicket(TicketType.Restaurant, "Da Giovanni");
        ticketController.addTicket(ticket);
        ticket.setPayerid(niels.getId());
        ticket.setPaid_amount(100.0);
        ticket.addOws(thijs.getId(), 30.0);
        ticket.addOws(maxim.getId(), 40.0);

        // Ticket 2
        Ticket ticket2 = ticketFactory.getTicket(TicketType.Airplane, "Vlucht Kreta");
        ticketController.addTicket(ticket2);
        ticket2.setPayerid(thijs.getId());
        ticket2.setPaid_amount(60.0);
        ticket2.addOws(niels.getId());
        ticket2.addOws(maxim.getId());

    }
}
