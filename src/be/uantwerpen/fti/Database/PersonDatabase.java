package be.uantwerpen.fti.Database;

import be.uantwerpen.fti.Person;

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
        support.firePropertyChange("added", true, p);
    }

    public Person getEntry(UUID id) {
        return this.db.get(id);
    }

    public void removeEntry(Person p) {
        db.remove(p.getId());
        support.firePropertyChange("remove", false, p);
    }

    public Person[] PersonArray() {
        ArrayList<Person> personArrayList = new ArrayList<>();
        for (UUID key : db.keySet()) {
            personArrayList.add(db.get(key));
        }
        return personArrayList.toArray(new Person[0]);
    }

}