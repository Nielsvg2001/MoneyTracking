package be.uantwerpen.fti;

import be.uantwerpen.fti.Controller.PersonController;
import be.uantwerpen.fti.Controller.TicketController;
import be.uantwerpen.fti.Database.PersonDatabase;
import be.uantwerpen.fti.Database.TicketDatabase;
import be.uantwerpen.fti.Factory.TicketFactory;
import be.uantwerpen.fti.Ticket.Ticket;
import be.uantwerpen.fti.Ticket.TicketType;

import java.util.UUID;

public class Main {

    public static void printTicket(Ticket ticket) {
        if (ticket.getPayerid() == null | ticket.getPaid_amount() == null)
            System.out.println(ticket.getTicketType() + " ->  " + ticket.getName());
        else {
            Person payer = PersonDatabase.getInstance().getEntry(ticket.getPayerid());
            System.out.println(ticket.getTicketType() + ": " + ticket.getName());
            System.out.println(payer.getName() + " betaalde: " + ticket.getPaid_amount());
            for (UUID personid : ticket.getOws().keySet()) {
                Person person = PersonDatabase.getInstance().getEntry(personid);
                System.out.println(person.getName() + " verschuldigd: " + ticket.getOws().get(personid));
            }
        }
        System.out.println();
    }


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
        Ticket ticket2 = ticketFactory.getTicket(TicketType.Taxi, "Taxi Station");
        ticketController.addTicket(ticket2);
        ticket2.setPayerid(thijs.getId());
        ticket2.setPaid_amount(60.0);
        ticket2.addOws(niels.getId(), 20.0);
        ticket2.addOws(maxim.getId(), 25.0);

        // Ticket 3
        Ticket ticket3 = ticketFactory.getTicket(TicketType.Airplane, "Vliegtuig Kreta");
        ticketController.addTicket(ticket3);
        ticket3.setPayerid(maxim.getId());
        ticket3.setPaid_amount(90.0);
        ticket3.addOws(niels.getId());
        ticket3.addOws(thijs.getId());
        ticket3.splitEqual();


        printTicket(ticket);
        printTicket(ticket2);
        printTicket(ticket3);

        Calculate calculate = new Calculate();
        calculate.person_total(niels.getId());
        calculate.person_total(thijs.getId());
        calculate.person_total(maxim.getId());

    }
}
