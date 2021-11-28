package be.uantwerpen.fti.Ticket;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class AirplaneTicket extends Ticket {
    public AirplaneTicket(String name) {
        super(name);
        this.setTicketType(TicketType.Airplane);
        try {
            this.setImage(ImageIO.read(new File("src/be/uantwerpen/fti/Assets/airplane.png")));
        } catch (IOException | IllegalArgumentException ex) {
            this.setImage(null);
        }
    }
}
