package be.uantwerpen.fti;

import be.uantwerpen.fti.Database.PersonDatabase;
import be.uantwerpen.fti.Database.TicketDatabase;
import be.uantwerpen.fti.Ticket.Ticket;

import java.util.ArrayList;
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
                debts.put(ticket.getPayerid(), debts.get(ticket.getPayerid()) + sum);
            else
                debts.put(ticket.getPayerid(), sum);

        }
        return debts;
    }

    public void calculate_total(){
        HashMap<UUID, Double> debts = calculate_debts();
        HashMap<UUID, HashMap<UUID, Double>> total = new HashMap<>();
        for(UUID personuuid: debts.keySet()){
            HashMap<UUID, Double> entry = new HashMap<>();
            for(UUID uuid: debts.keySet()){
                Double to_pay = debts.get(personuuid);
                Double to_get = debts.get(uuid);
                if(to_pay < 0 & to_get > 0){
                    if(to_get + to_pay < 0){
                        debts.put(personuuid, to_pay + to_get);
                        debts.put(uuid, 0.0);
                        entry.put(uuid, to_get);
                        total.put(personuuid,entry);
                    }
                    else if (to_get + to_pay == 0){
                        debts.put(uuid, 0.0);
                        debts.put(personuuid, 0.0);
                        entry.put(uuid, to_get);
                        total.put(personuuid,entry);
                    }
                    else{
                        debts.put(personuuid, 0.0);
                        debts.put(uuid, to_pay - to_get);
                        entry.put(uuid,to_pay);
                        total.put(personuuid,entry);

                    }
                }
            }
        }
        System.out.println("Done");
    }






}
