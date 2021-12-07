package be.uantwerpen.fti.Controller;

import be.uantwerpen.fti.Database.PersonDatabase;
import be.uantwerpen.fti.Person;

public class PersonController {
    private static PersonController single_instance = null;
    private final PersonDatabase db;

    private PersonController(PersonDatabase db) {
        this.db = db;
    }

    public static PersonController getInstance(PersonDatabase db) {
        if (single_instance == null)
            single_instance = new PersonController(db) {
            };
        return single_instance;
    }


    public void addPerson(Person p) {
        db.addEntry(p);
    }

    public void removePerson(Person p) {
        db.removeEntry(p);
    }
}
