package be.uantwerpen.fti;

public class Person {
    private String name;
    private String mail;
    private String GSMnummer;

    public Person(String name) {
        this.name = name;
        this.mail = null;
        this.GSMnummer = null;
    }

    public Person(String name, String mail, String GSMnummer) {
        this.name = name;
        this.mail = mail;
        this.GSMnummer = GSMnummer;
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

    public String getGSMnummer() {
        return GSMnummer;
    }

    public void setGSMnummer(String GSMnummer) {
        this.GSMnummer = GSMnummer;
    }
}
