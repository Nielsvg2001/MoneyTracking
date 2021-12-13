package be.uantwerpen.fti.Ticket;

// https://stackoverflow.com/questions/9083096/drawing-an-image-to-a-jpanel-within-a-jframe

import java.awt.*;
import java.util.HashMap;
import java.util.UUID;

public abstract class Ticket {

    private final UUID uuid = UUID.randomUUID();
    private final HashMap<UUID, Double> ows = new HashMap<>();
    private String name;
    private TicketType ticketType;
    private Image image;
    private UUID payerid;
    private Double paid_amount;

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

    public void splitEqual() {
        for (UUID personuuid : this.getOws().keySet())
            if (this.getOws().get(personuuid) == null)
                this.getOws().put(personuuid, this.getPaid_amount() / (this.getOws().size()));
    }

    @Override
    public String toString() {
        return this.ticketType + " ->" + this.name;
    }
}
