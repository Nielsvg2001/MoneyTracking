package be.uantwerpen.fti.Ticket;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class OtherTicket extends Ticket {
    public OtherTicket(String name) {
        super(name);
        this.setTicketType(TicketType.Other);
        try {
            this.setImage(ImageIO.read(new File("src/be/uantwerpen/fti/Assets/other.png")));
        } catch (IOException | IllegalArgumentException ex) {
            this.setImage(null);
        }
    }
}
