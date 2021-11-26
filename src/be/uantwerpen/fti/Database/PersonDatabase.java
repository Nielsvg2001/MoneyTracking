package be.uantwerpen.fti.Database;

import be.uantwerpen.fti.Person;

import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

public class PersonDatabase extends Database{

    private final HashMap<UUID, Person> db;
    private static PersonDatabase single_instance = null;

    private PersonDatabase() {
        this.db = new HashMap<>();
    }


    public static PersonDatabase getInstance()
    {
        if (single_instance == null)
            single_instance = new PersonDatabase() {
            };
        return single_instance;
    }


    public void addEntry(Person p) {
        this.db.put(p.getId(),p);
        //support.firePropertyChange("RegristrationDB add",e,re);
    }

    public void getEntry(Person p) {
        this.db.get(p.getId());
    }

    public void getEntry(UUID id) {
        this.db.get(id);
    }

    public void removeEntry(Person p) {
        db.remove(p.getId());
        //support.firePropertyChange("RegristrationDB add",e,re);
    }

    public void removeEntry(UUID id) {
        db.remove(id);
        //support.firePropertyChange("RegristrationDB add",e,re);
    }

    public void allelements(){
        System.out.println("database: ");
        for (UUID key : db.keySet()) {
            System.out.println("key: " + key + " value: " + db.get(key));
        }
    }

}