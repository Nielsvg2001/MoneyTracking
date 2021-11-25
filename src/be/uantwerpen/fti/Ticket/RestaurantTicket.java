package be.uantwerpen.fti.Ticket;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class RestaurantTicket extends Ticket {
    public RestaurantTicket(String name) {
        super(name);
        this.setType(TicketType.Restaurant);
        try {
            this.setImage(ImageIO.read(new File("src/be/uantwerpen/fti/Assets/restaurant.png")));
        } catch (IOException | IllegalArgumentException ex) {
            this.setImage(null);
        }
    }
}
