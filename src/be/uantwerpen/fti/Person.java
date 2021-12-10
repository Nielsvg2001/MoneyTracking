package be.uantwerpen.fti;

import java.util.UUID;

public class Person {
    private final UUID id;
    private String name;
    private String mail;
    private String GSMNummer;

    public Person(String name) {
        this.name = name;
        this.mail = null;
        this.GSMNummer = null;
        this.id = UUID.randomUUID();
    }

    public Person(String name, String mail, String GSMNummer) {
        this.name = name;
        this.mail = mail;
        this.GSMNummer = GSMNummer;
        this.id = UUID.randomUUID();
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

    @Override
    public String toString() {
        return this.name;
    }

}
