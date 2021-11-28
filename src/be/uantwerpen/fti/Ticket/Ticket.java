package be.uantwerpen.fti.Ticket;

// https://stackoverflow.com/questions/9083096/drawing-an-image-to-a-jpanel-within-a-jframe

import java.awt.*;
import java.util.UUID;

public abstract class Ticket {

    private final UUID uuid = UUID.randomUUID();
    private String name;
    private TicketType ticketType;
    private Image image;

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

}
