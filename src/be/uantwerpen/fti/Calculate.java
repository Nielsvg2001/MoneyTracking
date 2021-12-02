package be.uantwerpen.fti;

import be.uantwerpen.fti.Database.PersonDatabase;
import be.uantwerpen.fti.Database.TicketDatabase;
import be.uantwerpen.fti.Ticket.Ticket;

import java.util.HashMap;
import java.util.UUID;

public class Calculate {

    private final PersonDatabase personDatabase = PersonDatabase.getInstance();
    private final TicketDatabase ticketDatabase = TicketDatabase.getInstance();



    public Calculate() {
    }


    public HashMap<UUID, Double>  calculate_debts(){
        HashMap<UUID, Double> debts = new HashMap<>();
        for(Ticket ticket: ticketDatabase.ticketList()){

            Double sum = 0.0;
            for(UUID personuuid: ticket.getOws().keySet()){
                if(debts.containsKey(personuuid))
                    debts.put(personuuid, debts.get(personuuid) - ticket.getOws().get(personuuid));
                else
                    debts.put(personuuid, -ticket.getOws().get(personuuid));
                sum += ticket.getOws().get(personuuid);
            }

            if(debts.containsKey(ticket.getPayerid()))
                debts.put(ticket.getPayerid(), debts.get(ticket.getPayerid()) + ticket.getPaid_amount() - sum);
            else
                debts.put(ticket.getPayerid(), ticket.getPaid_amount() - sum);

        }
        return debts;
    }

    public HashMap<UUID, Double> calculate_total(Person person){
        return null;
    }






}
