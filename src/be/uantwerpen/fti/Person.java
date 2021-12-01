package be.uantwerpen.fti;

import be.uantwerpen.fti.Controller.PersonController;
import be.uantwerpen.fti.Database.PersonDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Person {
    private String name;
    private String mail;
    private String GSMNummer;
    private final UUID id;
    private HashMap<Person, Double> debtlist;

    public Person(String name) {
        this.name = name;
        this.mail = null;
        this.GSMNummer = null;
        this.id = UUID.randomUUID();
        this.debtlist = new HashMap<>();
    }

    public Person(String name, String mail, String GSMNummer) {
        this.name = name;
        this.mail = mail;
        this.GSMNummer = GSMNummer;
        this.id = UUID.randomUUID();
        this.debtlist = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getGSMNummer() {
        return GSMNummer;
    }

    public void setGSMNummer(String GSMNummer) {
        this.GSMNummer = GSMNummer;
    }

    public UUID getId() {
        return id;
    }

    public HashMap<Person, Double> getDebtlist() {
        return debtlist;
    }

    public void addDept(Person p, double debt){
        if (debtlist.containsKey(p)) {
            double oldDept = debtlist.get(p);
            debt = oldDept + debt;
        }
        debtlist.put(p,debt);
    }

    public void addDept(UUID id, double debt){
        addDept(PersonController.getInstance(PersonDatabase.getInstance()).getPerson(id),debt);
    }
}
