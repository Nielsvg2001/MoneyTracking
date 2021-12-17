package be.uantwerpen.fti;


import be.uantwerpen.fti.Controller.PersonController;
import be.uantwerpen.fti.Controller.TicketController;
import be.uantwerpen.fti.Factory.TicketFactory;
import be.uantwerpen.fti.GUI.ViewFrame;
import be.uantwerpen.fti.Ticket.Ticket;
import be.uantwerpen.fti.Ticket.TicketType;
import be.uantwerpen.fti.observers.PersonDatabaseObserver;
import be.uantwerpen.fti.observers.TicketDatabaseObserver;

public class Main {

    public static void main(String[] args) {

        /*
        TO DO:
            - Observer voor calculate
            - calulate alle personen weergeven -> voor test te maken ook
            - UnitTest
            - andere test
            - schema's

           - private zetten :
           * viewframe

        Bugs:

        extra:
            - kleur lijsten
            - vaste groote voor addticket velden + jlabels
            - dropdowns groot genoeg maken zodat de naam erinpast
         */

        // Init
        TicketFactory ticketFactory = new TicketFactory();
        TicketController ticketController = TicketController.getInstance();
        PersonController personController = PersonController.getInstance();
        TicketDatabaseObserver ticketDatabaseObserver = new TicketDatabaseObserver();
        PersonDatabaseObserver personDatabaseObserver = new PersonDatabaseObserver();
        personController.addObserver(personDatabaseObserver);
        ticketController.addObserver(ticketDatabaseObserver);



        // Maak de persoon current user ineens anders kent viewframe deze niet
        Person niels = new Person("Niels", "niels@uantwerpen.be", "0453503949");
        Login.getInstance().setCurrentUser(niels.getId());


        Person thijs = new Person("Thijs", "thijs@uantwerpen.be", "0487529926");
        Person maxim = new Person("Maxim", "maxim@uantwerpen.be", "0485930030");

        personController.addPerson(niels);
        personController.addPerson(thijs);
        personController.addPerson(maxim);

        // Ticket 1
        Ticket ticket = ticketFactory.getTicket(TicketType.Restaurant, "Da Giovanni");
        ticket.setPayerid(niels.getId());
        ticket.setPaid_amount(100.0);
        ticket.addOws(thijs.getId(), 30.0);
        ticket.addOws(maxim.getId(), 40.0);
        ticketController.addTicket(ticket);

        // Ticket 2
        Ticket ticket2 = ticketFactory.getTicket(TicketType.Taxi, "Taxi Station");
        ticket2.setPayerid(thijs.getId());
        ticket2.setPaid_amount(60.0);
        ticket2.addOws(niels.getId(), 20.0);
        ticket2.addOws(maxim.getId(), 25.0);
        ticketController.addTicket(ticket2);

        // Ticket 3
        Ticket ticket3 = ticketFactory.getTicket(TicketType.Airplane, "Vliegtuig Kreta");
        ticket3.setPayerid(maxim.getId());
        ticket3.setPaid_amount(90.0);
        ticket3.addOws(niels.getId());
        ticket3.addOws(thijs.getId());
        ticket3.splitEqual();
        ticketController.addTicket(ticket3);



        ViewFrame viewFrame = ViewFrame.getInstance();
    }
}
