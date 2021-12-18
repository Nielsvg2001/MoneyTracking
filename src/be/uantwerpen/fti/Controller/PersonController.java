package be.uantwerpen.fti.Controller;

import be.uantwerpen.fti.Database.PersonDatabase;
import be.uantwerpen.fti.Person;
import be.uantwerpen.fti.Observers.PersonDatabaseObserver;

import java.util.UUID;

public class PersonController {
    private static PersonController single_instance = null;
    private final PersonDatabase personDatabase = PersonDatabase.getInstance();

    private PersonController() {
    }

    public static PersonController getInstance() {
        if (single_instance == null)
            single_instance = new PersonController() {
            };
        return single_instance;
    }


    public void addPerson(Person p) {
        personDatabase.addEntry(p);
    }

    public Person getPerson(UUID uuid) {
        return personDatabase.getEntry(uuid);
    }

    public void removePerson(Person p) {
        personDatabase.removeEntry(p);
    }

    public Person[] personArray() {
        return personDatabase.PersonArray();
    }

    public void addObserver(PersonDatabaseObserver personDatabaseObserver) {
        personDatabase.addObserver(personDatabaseObserver);
    }
}
