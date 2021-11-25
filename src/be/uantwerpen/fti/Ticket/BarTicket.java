package be.uantwerpen.fti.Ticket;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class BarTicket extends Ticket {
    public BarTicket(String name) {
        super(name);
        this.setType(TicketType.Bar);
        try {
            this.setImage(ImageIO.read(new File("src/be/uantwerpen/fti/Assets/bar.png")));
        } catch (IOException | IllegalArgumentException ex) {
            this.setImage(null);
        }
    }
}
