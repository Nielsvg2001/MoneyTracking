package be.uantwerpen.fti.Ticket;

// https://stackoverflow.com/questions/9083096/drawing-an-image-to-a-jpanel-within-a-jframe

import be.uantwerpen.fti.Database.PersonDatabase;
import be.uantwerpen.fti.Person;

import java.awt.*;
import java.util.HashMap;
import java.util.UUID;

public abstract class Ticket {

    private final UUID uuid = UUID.randomUUID();
    private String name;
    private TicketType ticketType;
    private Image image;
    private UUID payerid;
    private Double paid_amount;
    private final HashMap<UUID, Double> ows = new HashMap<>();

    public Ticket(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public UUID getUuid() {
        return uuid;
    }

    public UUID getPayerid() {
        return payerid;
    }

    public void setPayerid(UUID payerid) {
        this.payerid = payerid;
    }

    public Double getPaid_amount() {
        return paid_amount;
    }

    public void setPaid_amount(Double paid_amount) {
        this.paid_amount = paid_amount;
    }

    public void addOws(UUID personid, Double amount) {
        this.ows.put(personid, amount);
    }

    public void addOws(UUID personid) {
        this.ows.put(personid, null);
    }


    public HashMap<UUID, Double> getOws() {
        return ows;
    }

    @Override
    public String toString() {
        if (this.payerid == null | this.paid_amount == null)
            return this.getTicketType() + ": " + this.getName();
        else {
            Person payer = PersonDatabase.getInstance().getEntry(this.payerid);
            StringBuilder string = new StringBuilder(this.getTicketType() + ": " + this.getName() + "\n");
            string.append(payer.getName()).append(" betaalde: ").append(this.paid_amount);
            for (UUID personid : this.ows.keySet()) {
                Person person = PersonDatabase.getInstance().getEntry(personid);
                string.append("\n").append(person.getName()).append(" verschuldigd: ").append(this.ows.get(personid));
            }
            return "" + string;
        }
    }
}
