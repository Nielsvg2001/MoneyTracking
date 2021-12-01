package be.uantwerpen.fti.Ticket;

// https://stackoverflow.com/questions/9083096/drawing-an-image-to-a-jpanel-within-a-jframe

import be.uantwerpen.fti.Person;

import java.awt.*;
import java.util.HashMap;
import java.util.UUID;

public abstract class Ticket {

    private final UUID uuid = UUID.randomUUID();
    private String name;
    private TicketType ticketType;
    private Image image;
    private Person payer;
    private Double paid_amount;
    private HashMap<Person, Double> ows = new HashMap<>();

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

    public Person getPayer() {
        return payer;
    }

    public void setPayer(Person payer) {
        this.payer = payer;
    }

    public Double getPaid_amount() {
        return paid_amount;
    }

    public void setPaid_amount(Double paid_amount) {
        this.paid_amount = paid_amount;
    }

    public void addOws(Person person, Double amount){
        this.ows.put(person, amount);
    }

    public void addOws(Person person){
        this.ows.put(person, null);
    }


    public HashMap<Person, Double> getOws() {
        return ows;
    }

    @Override
    public String toString(){
        if(this.payer == null | this.paid_amount == null)
            return this.getTicketType() + ": " + this.getName();
        else{
            StringBuilder string = new StringBuilder(this.getTicketType() + ": " + this.getName() + "\n");
            string.append(this.payer.getName()).append(" betaalde: ").append(this.paid_amount);
            for(Person person: this.ows.keySet()){
                string.append("\n").append(person.getName()).append(" verschuldigd: ").append(this.ows.get(person));
            }
            return "" + string;
        }
    }
}
