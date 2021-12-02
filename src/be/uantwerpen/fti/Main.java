package be.uantwerpen.fti;

import be.uantwerpen.fti.Controller.TicketController;
import be.uantwerpen.fti.Database.PersonDatabase;
import be.uantwerpen.fti.Database.TicketDatabase;
import be.uantwerpen.fti.Factory.TicketFactory;
import be.uantwerpen.fti.Ticket.Ticket;
import be.uantwerpen.fti.Ticket.TicketType;
import be.uantwerpen.fti.observers.PersonDatabaseObserver;
import be.uantwerpen.fti.observers.TicketDatabaseObserver;

public class Main {

    public static void main(String[] args) {
        // Ticket factory test

        TicketFactory ticketFactory = new TicketFactory();
        Ticket ticket = ticketFactory.getTicket(TicketType.Restaurant, "Da Giovanni");
        Ticket ticket2 = ticketFactory.getTicket(TicketType.Airplane, "Vlucht naar kreta");
        Ticket ticket3 = ticketFactory.getTicket(TicketType.Taxi, "Taxi naar hotel");
        Ticket ticket4 = ticketFactory.getTicket(TicketType.Concert, "Conecert RHP");

        // Test ticketdatabase
        TicketDatabase ticketDatabase = TicketDatabase.getInstance();
        TicketController ticketController = TicketController.getInstance(ticketDatabase);
        TicketDatabaseObserver ticketDatabaseObserver = new TicketDatabaseObserver();
        ticketDatabase.addObserver(ticketDatabaseObserver);

        ticketController.addTicket(ticket);
        ticketController.addTicket(ticket2);
        ticketController.addTicket(ticket3);
        ticketController.addTicket(ticket4);

        ticketController.removeTicket(ticket3);

        /*
        System.out.println("Test database volledig");
        System.out.println(ticketController.ticketList());

        ticketController.removeTicket(ticket3);
        System.out.println();

        System.out.println("Test database na enkele verwijderingen");
        System.out.println(ticketController.ticketList());
        */

        System.out.println();

        //test Person
        Person p1 = new Person("jos");
        Person p2 = new Person("jos","jos@ua.com","01236");
        Person p3 = new Person("jos");
        System.out.println(p1.getName());
        System.out.println(p2.getName());
        System.out.println(p2.getGSMNummer());
        System.out.println(p2.getMail());
        p2.setMail("testmail");
        System.out.println(p2.getMail());
        System.out.println(p1.getMail());
        p1.setMail("p1mail");
        System.out.println(p1.getMail());
        System.out.println(p1.getGSMNummer());

        System.out.println(p1.getId());
        System.out.println(p2.getId());
        System.out.println(p3.getId());

        // test persondatabase
        PersonDatabase pdb = PersonDatabase.getInstance();
        PersonDatabaseObserver pdbO = new PersonDatabaseObserver();
        pdb.addObserver(pdbO);
        pdb.addEntry(p1);
        pdb.allelements();
        pdb.addEntry(p2);
        pdb.allelements();
        pdb.addEntry(p2);
        pdb.allelements();
        pdb.addEntry(p3);
        pdb.allelements();
        pdb.removeEntry(p2);
        pdb.allelements();

        /* TO DO:
            -single  database person -> singleton
            - singel database tickets -> singleton
            - 2 controllers for database
            - factory to create tickets?
            - observer to observe databases
            - nog design patern niet gezien in practicum
            - GUI

            - unit test
            - integration test


        gitkraken
                sourcetree
*/
    }
}
