package be.uantwerpen.fti.Database;

import be.uantwerpen.fti.Person;
import be.uantwerpen.fti.Ticket.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PersonDatabase extends Database {

    private static PersonDatabase single_instance = null;
    private final HashMap<UUID, Person> db;


    private PersonDatabase() {
        this.db = new HashMap<>();
    }


    public static PersonDatabase getInstance() {
        if (single_instance == null)
            single_instance = new PersonDatabase() {
            };
        return single_instance;
    }


    public void addEntry(Person p) {
        this.db.put(p.getId(), p);
        support.firePropertyChange("added", p, p.getId());
    }

    public Person getEntry(UUID id) {
        return this.db.get(id);
    }

    public void removeEntry(Person p) {
        db.remove(p.getId());
        support.firePropertyChange("remove", p, p.getId());
    }

    public void removeEntry(UUID id) {
        db.remove(id);
        support.firePropertyChange("remove", id, "niks");
    }

    public ArrayList<Person> PersonList() {
        ArrayList<Person> PersonList = new ArrayList<>();
        for (UUID key : db.keySet()) {
            PersonList.add(db.get(key));
        }
        return PersonList;
    }

}