package be.uantwerpen.fti.Ticket;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ConcertTicket extends Ticket {
    public ConcertTicket(String name) {
        super(name);
        this.setTicketType(TicketType.Concert);
        try {
            this.setImage(ImageIO.read(new File("src/be/uantwerpen/fti/Assets/concert.png")));
        } catch (IOException | IllegalArgumentException ex) {
            this.setImage(null);
        }

    }
}
