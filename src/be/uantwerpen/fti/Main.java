package be.uantwerpen.fti;

import be.uantwerpen.fti.Factory.TicketFactory;
import be.uantwerpen.fti.Ticket.Ticket;
import be.uantwerpen.fti.Ticket.TicketType;

public class Main {

    public static void main(String[] args) {
        TicketFactory ticketFactory = new TicketFactory();
        Ticket ticket = ticketFactory.getTicket(TicketType.Restaurant, "Dit is een test");
        System.out.println(ticket.getName());
        System.out.println(ticket.getType());
        System.out.println(ticket.getImage());

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
