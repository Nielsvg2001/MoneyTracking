package be.uantwerpen.fti;

import be.uantwerpen.fti.Database.PersonDatabase;
import be.uantwerpen.fti.Factory.TicketFactory;
import be.uantwerpen.fti.Ticket.Ticket;
import be.uantwerpen.fti.Ticket.TicketType;
import be.uantwerpen.fti.observers.PersonDatabaseObserver;

import java.util.Iterator;

public class Main {

    public static void main(String[] args) {


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
/*
        //test ticketFactory
        TicketFactory ticketFactory = new TicketFactory();
        Ticket ticket = ticketFactory.getTicket(TicketType.Restaurant, "Dit is een test");
        System.out.println(ticket.getName());
        System.out.println(ticket.getType());
        System.out.println(ticket.getImage());
*/
        //test Person
        Person p1 = new Person("jos");
        Person p2 = new Person("jos","jos@ua.com","01236");
        Person p3 = new Person("jos");
        /*
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
*/
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







    }
}
