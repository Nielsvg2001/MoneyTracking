package Tests;

import be.uantwerpen.fti.Calculate;
import be.uantwerpen.fti.Controller.PersonController;
import be.uantwerpen.fti.Controller.TicketController;
import be.uantwerpen.fti.Factory.TicketFactory;
import be.uantwerpen.fti.Person;
import be.uantwerpen.fti.Ticket.Ticket;
import be.uantwerpen.fti.Ticket.TicketType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.UUID;

public class GlobalITest {

    public GlobalITest()
    {

    }

    @Before
    public void initialize()
    {
        TicketController ticketController = TicketController.getInstance();
        PersonController personController = PersonController.getInstance();
        for(Ticket t: ticketController.ticketArray()){
            ticketController.removeTicket(t);
        }
        for(Person p: personController.personArray()){
            personController.removePerson(p);
        }
    }

    @Test
    public void simple_splitnotequal()
    {
        TicketFactory ticketFactory = new TicketFactory();
        TicketController ticketController = TicketController.getInstance();
        PersonController personController = PersonController.getInstance();

        Person niels = new Person("Niels", "niels@uantwerpen.be", "0453503949");
        Person thijs = new Person("Thijs", "thijs@uantwerpen.be", "0487529926");
        Person maxime = new Person("Maxim", "maxim@uantwerpen.be", "0485930030");
        personController.addPerson(niels);
        personController.addPerson(thijs);
        personController.addPerson(maxime);



        // Ticket 1
        Ticket ticket = ticketFactory.getTicket(TicketType.Restaurant, "Da Giovanni");
        ticketController.addTicket(ticket);
        ticket.setPayerid(niels.getId());
        ticket.setPaid_amount(100.0);
        ticket.addOws(thijs.getId(), 30.0); //T->N 30
        ticket.addOws(maxime.getId(), 40.0); //M->N40

        // T:-30
        // M: -40
        // N: 70
        Calculate calculate = new Calculate();
        HashMap<UUID, HashMap<UUID, Double>> total = calculate.calculate_total();
        for(UUID person: total.keySet()){
            HashMap<UUID, Double> ows = total.get(person);
            double totaltoget = 0;
            for(UUID person2: ows.keySet()) {
                double toget = ows.get(person2);
                totaltoget = totaltoget+toget;
            }
            if (person == thijs.getId()) {
                Assert.assertEquals("to pay Thijs klopt niet",-30, totaltoget, 10^-5);
            } else if (person == niels.getId()) {
                Assert.assertEquals("to pay Niels klopt niet", 70, totaltoget,10^-5);
            } else if (person == maxime.getId()) {
                Assert.assertEquals("to pay Maxime klopt niet", -40, totaltoget,10^-5);
            }
        }

        // Ticket 2
        Ticket ticket2 = ticketFactory.getTicket(TicketType.Taxi, "Taxi Station");
        ticketController.addTicket(ticket2);
        ticket2.setPayerid(thijs.getId());
        ticket2.setPaid_amount(60.0);
        ticket2.addOws(niels.getId(), 20.0); //N->T 20
        ticket2.addOws(maxime.getId(), 25.0); //M->T 25

        // T:15
        // M: -65
        // N: 50

        calculate = new Calculate();
        total = calculate.calculate_total();
        for(UUID person: total.keySet()){
            HashMap<UUID, Double> ows = total.get(person);
            double totaltoget = 0;
            for(UUID person2: ows.keySet()) {
                double toget = ows.get(person2);
                totaltoget = totaltoget+toget;
            }
            if (person == thijs.getId()) {
                Assert.assertEquals("to pay Thijs klopt niet2",15, totaltoget, 10^-5);
            } else if (person == niels.getId()) {
                Assert.assertEquals("to pay Niels klopt niet2",50, totaltoget, 10^-5);
            } else if (person == maxime.getId()) {
                Assert.assertEquals("to pay Maxime klopt niet2",-65, totaltoget, 10^-5);
            }
        }
    }


    @Test
    public void simple_splitequal()
    {
        TicketFactory ticketFactory = new TicketFactory();
        TicketController ticketController = TicketController.getInstance();
        PersonController personController = PersonController.getInstance();

        Person niels = new Person("Niels", "niels@uantwerpen.be", "0453503949");
        Person thijs = new Person("Thijs", "thijs@uantwerpen.be", "0487529926");
        Person maxime = new Person("Maxim", "maxim@uantwerpen.be", "0485930030");
        personController.addPerson(niels);
        personController.addPerson(thijs);
        personController.addPerson(maxime);

        // Ticket 1
        Ticket ticket1 = ticketFactory.getTicket(TicketType.Airplane, "Vliegtuig Kreta");
        ticketController.addTicket(ticket1);
        ticket1.setPayerid(maxime.getId());
        ticket1.setPaid_amount(90.0);
        ticket1.addOws(niels.getId()); //N->M 45
        ticket1.addOws(thijs.getId()); //T->M 45
        ticket1.splitEqual();


        Calculate calculate = new Calculate();
        HashMap<UUID, HashMap<UUID, Double>> total = calculate.calculate_total();
        for(UUID person: total.keySet()){
            HashMap<UUID, Double> ows = total.get(person);
            double totaltoget = 0;
            for(UUID person2: ows.keySet()) {
                double toget = ows.get(person2);
                totaltoget = totaltoget+toget;
            }
            if (person == thijs.getId()) {
                Assert.assertEquals("to pay Thijs klopt niet3",  -45.0, totaltoget,10^-5);
            } else if (person == niels.getId()) {
                Assert.assertEquals("to pay Niels klopt niet3", -45.0, totaltoget,10^-5);
            } else if (person == maxime.getId()) {
                Assert.assertEquals("to pay Maxime klopt niet3", 90, totaltoget,10^-5);
            }
        }
    }

    @Test
    public void simple_splitequalAll()
    {
        TicketFactory ticketFactory = new TicketFactory();
        TicketController ticketController = TicketController.getInstance();
        PersonController personController = PersonController.getInstance();

        Person niels = new Person("Niels", "niels@uantwerpen.be", "0453503949");
        Person thijs = new Person("Thijs", "thijs@uantwerpen.be", "0487529926");
        Person maxime = new Person("Maxim", "maxim@uantwerpen.be", "0485930030");
        personController.addPerson(niels);
        personController.addPerson(thijs);
        personController.addPerson(maxime);

        // Ticket 1
        Ticket ticket1 = ticketFactory.getTicket(TicketType.Airplane, "Vliegtuig Kreta");
        ticketController.addTicket(ticket1);
        ticket1.setPayerid(maxime.getId());
        ticket1.setPaid_amount(90.0);
        ticket1.addOws(niels.getId()); //N->M 30
        ticket1.addOws(thijs.getId()); //T->M 30
        ticket1.addOws(maxime.getId());
        ticket1.splitEqual();


        Calculate calculate = new Calculate();
        HashMap<UUID, HashMap<UUID, Double>> total = calculate.calculate_total();
        for(UUID person: total.keySet()){
            HashMap<UUID, Double> ows = total.get(person);
            double totaltoget = 0;
            for(UUID person2: ows.keySet()) {
                double toget = ows.get(person2);
                totaltoget = totaltoget+toget;
            }
            if (person == thijs.getId()) {
                Assert.assertEquals("to pay Thijs klopt niet",  -30.0, totaltoget,10^-5);
            } else if (person == niels.getId()) {
                Assert.assertEquals("to pay Niels klopt niet", -30.0, totaltoget,10^-5);
            } else if (person == maxime.getId()) {
                Assert.assertEquals("to pay Maxime klopt niet", 60, totaltoget,10^-5);
            }
        }
    }
}