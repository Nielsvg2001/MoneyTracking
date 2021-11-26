package be.uantwerpen.fti.Ticket;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class TaxiTicket extends Ticket {
    public TaxiTicket(String name) {
        super(name);
        this.setTicketType(TicketType.Taxi);
        try {
            this.setImage(ImageIO.read(new File("src/be/uantwerpen/fti/Assets/taxi.png")));
        } catch (IOException | IllegalArgumentException ex) {
            this.setImage(null);
        }
    }
}
