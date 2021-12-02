package be.uantwerpen.fti;

import be.uantwerpen.fti.Database.TicketDatabase;
import be.uantwerpen.fti.Ticket.Ticket;

import java.util.HashMap;
import java.util.UUID;

public class Person {
    private String name;
    private String mail;
    private String GSMNummer;
    private final UUID id;

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

    public HashMap<UUID, Double> calculate() {
        HashMap<UUID, Double> dept = new HashMap<>();
        for (Ticket ticket : TicketDatabase.getInstance().ticketList()) {
            for (UUID personuuid : ticket.getOws().keySet()) {

                if (ticket.getOws().get(personuuid) == null)
                    ticket.getOws().put(personuuid, ticket.getPaid_amount() / (ticket.getOws().size() + 1));

                if (personuuid == this.id) {
                    if (dept.containsKey(ticket.getPayerid()))
                        dept.put(ticket.getPayerid(), dept.get(ticket.getPayerid()) - ticket.getOws().get(this.id));
                    else
                        dept.put(ticket.getPayerid(), -ticket.getOws().get(this.id));
                }

                if (ticket.getPayerid() == this.id) {
                    if (dept.containsKey(personuuid))
                        dept.put(personuuid, dept.get(personuuid) + ticket.getOws().get(personuuid));
                    else
                        dept.put(personuuid, ticket.getOws().get(personuuid));
                }
            }
        }
        return dept;
    }
}
