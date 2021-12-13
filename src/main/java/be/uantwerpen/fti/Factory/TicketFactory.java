package be.uantwerpen.fti.Factory;


import be.uantwerpen.fti.Ticket.*;

public class TicketFactory {
    public TicketFactory() {
    }

    public Ticket getTicket(TicketType ticketType, String name) {
        switch (ticketType) {
            case Airplane:
                return new AirplaneTicket(name);
            case Bar:
                return new BarTicket(name);
            case Cinema:
                return new CinemaTicket(name);
            case Concert:
                return new ConcertTicket(name);
            case Restaurant:
                return new RestaurantTicket(name);
            case Taxi:
                return new TaxiTicket(name);
            default:
                return new OtherTicket(name);
        }
    }
}
