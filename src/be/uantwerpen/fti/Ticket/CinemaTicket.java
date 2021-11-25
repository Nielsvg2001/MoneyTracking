package be.uantwerpen.fti.Ticket;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class CinemaTicket extends Ticket {
    public CinemaTicket(String name) {
        super(name);
        this.setType(TicketType.Cinema);
        try {
            this.setImage(ImageIO.read(new File("src/be/uantwerpen/fti/Assets/cinema.png")));
        } catch (IOException | IllegalArgumentException ex) {
            this.setImage(null);
        }
    }
}
