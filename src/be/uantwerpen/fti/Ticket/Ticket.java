package be.uantwerpen.fti.Ticket;

// https://stackoverflow.com/questions/9083096/drawing-an-image-to-a-jpanel-within-a-jframe

import be.uantwerpen.fti.Controller.PersonController;
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
    private Person payer;
    private Double paid_amount;
    private HashMap<Person, Double> owsList =  new HashMap<>();

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

    public HashMap<Person, Double> getOwsList() {
        return owsList;
    }

    @Override
    public String toString(){
        StringBuilder list = new StringBuilder();
        if (this.payer != null & this.paid_amount != null) {
            list.append("\n").append(this.payer.getName()).append(" betaald: ").append(this.paid_amount);
            for (Person person : owsList.keySet()) {
                list.append("\n").append(person.getName()).append(" bedrag: ").append(owsList.get(person));
            }
        }
        return this.getTicketType() + ": " + this.getName() + list;
    }

    public void addOws(Person person, Double amount){
        if (owsList.containsKey(person))
            owsList.put(person, owsList.get(person) + amount);
        else
            owsList.put(person, amount);
    }

    public void addOws(UUID uuid, Double amount){
        addOws(PersonController.getInstance(PersonDatabase.getInstance()).getPerson(uuid), amount);
    }
}
