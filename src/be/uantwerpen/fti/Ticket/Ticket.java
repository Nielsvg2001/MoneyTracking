package be.uantwerpen.fti.Ticket;

// https://stackoverflow.com/questions/9083096/drawing-an-image-to-a-jpanel-within-a-jframe

import java.awt.*;

public abstract class Ticket {

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

    public TicketType getType() {
        return ticketType;
    }

    public void setType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}
