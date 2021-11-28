package be.uantwerpen.fti;

import be.uantwerpen.fti.Controller.TicketController;
import be.uantwerpen.fti.Database.TicketDatabase;
import be.uantwerpen.fti.Factory.TicketFactory;
import be.uantwerpen.fti.Ticket.Ticket;
import be.uantwerpen.fti.Ticket.TicketType;

public class Main {

    public static void main(String[] args) {
        // Ticket factory test

        TicketFactory ticketFactory = new TicketFactory();
        Ticket ticket = ticketFactory.getTicket(TicketType.Restaurant, "Da Giovanni");
        Ticket ticket2 = ticketFactory.getTicket(TicketType.Airplane, "Vlucht naar kreta");
        Ticket ticket3 = ticketFactory.getTicket(TicketType.Taxi, "Taxi naar hotel");
        Ticket ticket4 = ticketFactory.getTicket(TicketType.Concert, "Conecert RHP");
        System.out.println(ticket.getName());
        System.out.println(ticket.getTicketType());
        System.out.println(ticket.getImage());

        // Test ticketdatabase
        TicketDatabase ticketDatabase = TicketDatabase.getInstance();
        TicketController ticketController = TicketController.getInstance(ticketDatabase);

        ticketController.addTicket(ticket);
        ticketController.addTicket(ticket2);
        ticketController.addTicket(ticket3);
        ticketController.addTicket(ticket4);


        System.out.println("Test database volledig");

        for (Ticket iter : ticketController.ticketList()) {
            System.out.println(iter.getTicketType() + ": " + iter.getName());
        }

        ticketController.removeTicket(ticket3);
        System.out.println();

        System.out.println("Test database na enkele verwijderingen");
        for (Ticket iter : ticketController.ticketList()) {
            System.out.println(iter.getTicketType() + ": " + iter.getName());
        }

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
