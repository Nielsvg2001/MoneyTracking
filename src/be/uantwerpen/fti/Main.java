package be.uantwerpen.fti;

import be.uantwerpen.fti.Controller.PersonController;
import be.uantwerpen.fti.Controller.TicketController;
import be.uantwerpen.fti.Database.Database;
import be.uantwerpen.fti.Database.PersonDatabase;
import be.uantwerpen.fti.Database.TicketDatabase;
import be.uantwerpen.fti.Factory.TicketFactory;
import be.uantwerpen.fti.Ticket.ConcertTicket;
import be.uantwerpen.fti.Ticket.Ticket;
import be.uantwerpen.fti.Ticket.TicketType;
import be.uantwerpen.fti.observers.PersonDatabaseObserver;
import be.uantwerpen.fti.observers.TicketDatabaseObserver;

import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        System.out.println("Test Ticket database");


        // Init
        TicketFactory ticketFactory = new TicketFactory();
        TicketDatabase ticketDatabase = TicketDatabase.getInstance();
        PersonDatabase personDatabase = PersonDatabase.getInstance();
        TicketController ticketController = TicketController.getInstance(ticketDatabase);
        PersonController personController = PersonController.getInstance(personDatabase);
        TicketDatabaseObserver ticketDatabaseObserver = new TicketDatabaseObserver();
        PersonDatabaseObserver personDatabaseObserver = new PersonDatabaseObserver();

        ticketDatabase.addObserver(ticketDatabaseObserver);
        personDatabase.addObserver(personDatabaseObserver);

        Ticket ticket = ticketFactory.getTicket(TicketType.Restaurant, "Da Giovanni");
        Person niels = new Person("Niels","niels@uantwerpen.be", "0453503949");
        Person thijs = new Person("Thijs","thijs@uantwerpen.be", "0487529926");
        Person maxim = new Person("Maxim", "maxim@uantwerpen.be", "0485930030");

        personController.addPerson(niels);
        personController.addPerson(thijs);
        personController.addPerson(maxim);

        // Start ticket
        ticketController.addTicket(ticket);
        ticket.setPayer(niels);
        ticket.setPaid_amount(70.0);
        ticket.addOws(thijs,30.0);
        ticket.addOws(maxim, 35.0);

        System.out.println(ticket);

        ticketController.split(ticket);

        System.out.println();
        System.out.println();
        System.out.println(niels.getName());
        for(Person person: niels.getDebtlist().keySet()){
            System.out.println(person.getName() + " bedrag: " + niels.getDebtlist().get(person));
        }

        System.out.println();
        System.out.println();
        System.out.println(thijs.getName());
        for(Person person: thijs.getDebtlist().keySet()){
            System.out.println(person.getName() + " bedrag: " + thijs.getDebtlist().get(person));
        }

        System.out.println();
        System.out.println();
        System.out.println(maxim.getName());
        for(Person person: maxim.getDebtlist().keySet()){
            System.out.println(person.getName() + " bedrag: " + maxim.getDebtlist().get(person));
        }


        System.out.println();
        System.out.println();
        Ticket ticket2 = ticketFactory.getTicket(TicketType.Taxi, "Taxi naar station");
        ticketController.addTicket(ticket2);
        ticket2.setPayer(thijs);
        ticket2.setPaid_amount(100.0);
        ticket2.addOws(niels,20.0);
        ticket2.addOws(maxim, 40.0);
        ticketController.split(ticket2);

        System.out.println(ticket2);

        System.out.println();
        System.out.println();
        System.out.println(niels.getName());
        for(Person person: niels.getDebtlist().keySet()){
            System.out.println(person.getName() + " bedrag: " + niels.getDebtlist().get(person));
        }

        System.out.println();
        System.out.println();
        System.out.println(thijs.getName());
        for(Person person: thijs.getDebtlist().keySet()){
            System.out.println(person.getName() + " bedrag: " + thijs.getDebtlist().get(person));
        }

        System.out.println();
        System.out.println();
        System.out.println(maxim.getName());
        for(Person person: maxim.getDebtlist().keySet()){
            System.out.println(person.getName() + " bedrag: " + maxim.getDebtlist().get(person));
        }
    }
}
