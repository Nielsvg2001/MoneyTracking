package be.uantwerpen.fti;

import be.uantwerpen.fti.Controller.PersonController;
import be.uantwerpen.fti.Controller.TicketController;
import be.uantwerpen.fti.Ticket.Ticket;

import java.util.HashMap;
import java.util.UUID;

public class Calculate {

    private final PersonController personController = PersonController.getInstance();
    private final TicketController ticketController = TicketController.getInstance();


    public Calculate() {
    }


    public HashMap<UUID, Double> calculate_debts() {
        HashMap<UUID, Double> debts = new HashMap<>();
        for (Ticket ticket : ticketController.ticketArray()) {

            Double sum = 0.0;
            for (UUID personuuid : ticket.getOws().keySet()) {
                if (debts.containsKey(personuuid))
                    debts.put(personuuid, debts.get(personuuid) - ticket.getOws().get(personuuid));
                else
                    debts.put(personuuid, -ticket.getOws().get(personuuid));
                sum += ticket.getOws().get(personuuid);
            }

            if (debts.containsKey(ticket.getPayerid()))
                debts.put(ticket.getPayerid(), debts.get(ticket.getPayerid()) + sum);
            else
                debts.put(ticket.getPayerid(), sum);
        }
        return debts;
    }

    public HashMap<UUID, HashMap<UUID, Double>> calculate_total() {
        HashMap<UUID, Double> debts = calculate_debts();

        // Init total HashMap
        HashMap<UUID, HashMap<UUID, Double>> total = new HashMap<>();
        for (UUID personuuid : debts.keySet()) {
            total.put(personuuid, new HashMap<>());
            for (UUID uuid : debts.keySet()) {
                if (uuid != personuuid)
                    total.get(personuuid).put(uuid, 0.0);
            }
        }

        // Calculate
        for (UUID personuuid : debts.keySet()) {
            Double to_pay = debts.get(personuuid);
            if (to_pay < 0) {
                for (UUID uuid : debts.keySet()) {
                    Double to_get = debts.get(uuid);
                    if (to_get > 0) {
                        if (to_get + to_pay < 0) {
                            debts.put(personuuid, to_pay + to_get);
                            debts.put(uuid, 0.0);
                            total.get(personuuid).put(uuid, -to_get);
                            total.get(uuid).put(personuuid, to_get);
                        } else if (to_get + to_pay == 0) {
                            debts.put(uuid, 0.0);
                            debts.put(personuuid, 0.0);
                            total.get(personuuid).put(uuid, -to_get);
                            total.get(uuid).put(personuuid, to_get);
                        } else {
                            debts.put(personuuid, 0.0);
                            debts.put(uuid, to_get + to_pay);
                            total.get(personuuid).put(uuid, to_pay);
                            total.get(uuid).put(personuuid, -to_pay);
                        }
                    }
                }
            }
        }
        return total;
    }

    public HashMap<UUID, Double> person_total(UUID personuuid) {
        HashMap<UUID, HashMap<UUID, Double>> total = calculate_total();

        System.out.println(personController.getPerson(personuuid).getName());
        for (UUID uuid1 : total.get(personuuid).keySet())
            System.out.println(personController.getPerson(uuid1).getName() + " -> " + total.get(personuuid).get(uuid1));
        System.out.println();
        return total.get(personuuid);

    }

}
