package be.uantwerpen.fti;

import be.uantwerpen.fti.Database.TicketDatabase;
import be.uantwerpen.fti.Ticket.Ticket;

import java.util.HashMap;
import java.util.UUID;

public class Person {
    private String name;
    private String mail;
    private String GSMNummer;
    private UUID id;

    public Person(String name) {
        this.name = name;
        this.mail = null;
        this.GSMNummer = null;
        this.id = UUID.randomUUID();
    }

    public Person(String name, String mail, String GSMNummer) {
        this.name = name;
        this.mail = mail;
        this.GSMNummer = GSMNummer;
        this.id = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getGSMNummer() {
        return GSMNummer;
    }

    public void setGSMNummer(String GSMNummer) {
        this.GSMNummer = GSMNummer;
    }

    public UUID getId() {
        return id;
    }

    public HashMap<Person, Double> calculate() {
        HashMap<Person, Double> dept = new HashMap<>();
        for(Ticket ticket: TicketDatabase.getInstance().ticketList()){
            // Waarom kan ik bij bv ticket.getOws().get(person) niet ticket.getOws().get(this) gebruiken?
            if(this.id == ticket.getPayer().id){
                for(Person person: ticket.getOws().keySet()){
                    Double amount = ticket.getOws().get(person) != null ? ticket.getOws().get(person) : ticket.getPaid_amount()/ (ticket.getOws().size() + 1);
                    if(dept.containsKey(person))
                        dept.put(person, dept.get(person) + amount);
                    else
                        dept.put(person, amount);
                }
            }
            if(ticket.getOws().containsKey(this)){
                Double amount = ticket.getOws().get(this) != null ? ticket.getOws().get(this) : ticket.getPaid_amount()/ (ticket.getOws().size() + 1);
                if(dept.containsKey(ticket.getPayer()))
                    dept.put(ticket.getPayer(), dept.get(ticket.getPayer()) - amount);
                else
                    dept.put(ticket.getPayer(), -amount);
            }
        }
        return dept;
    }
}
