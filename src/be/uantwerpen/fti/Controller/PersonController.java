package be.uantwerpen.fti.Controller;

import be.uantwerpen.fti.Database.PersonDatabase;
import be.uantwerpen.fti.Person;

import java.util.UUID;

public class PersonController {
    private PersonDatabase db;
    private static PersonController single_instance = null;

    private PersonController(PersonDatabase db)
    {
        this.db = db;
    }

    public static PersonController getInstance(PersonDatabase db)    {
        if (single_instance == null)
            single_instance = new PersonController(db) {
            };
        return single_instance;
    }


    public void addPerson(Person p)
    {
        db.addEntry(p);
    }

    public void removePerson(Person p)
    {
        db.removeEntry(p);
    }

    public Person getPerson(UUID id)
    {
        return db.getEntry(id);
    }
}
