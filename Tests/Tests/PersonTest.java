package Tests;

import be.uantwerpen.fti.Calculate;
import be.uantwerpen.fti.Controller.PersonController;
import be.uantwerpen.fti.Controller.TicketController;
import be.uantwerpen.fti.Factory.TicketFactory;
import be.uantwerpen.fti.Person;
import be.uantwerpen.fti.Ticket.Ticket;
import be.uantwerpen.fti.Ticket.TicketType;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class PersonTest {

    @Test
    public void getMethods()
    {
       Person testPerson = new Person("testnaam","testmail","04964");
       Assert.assertEquals("naam klopt niet","testnaam",testPerson.getName());
       Assert.assertEquals("nummer klopt niet","04964",testPerson.getGSMNummer());
       Assert.assertEquals("mail klopt niet","testmail",testPerson.getMail());
    }

    @Test
    public void setMethods()
    {
        Person testPerson = new Person("testnaam","testmail","04964");
        testPerson.setGSMNummer("09645");
        Assert.assertEquals("naam klopt niet","testnaam",testPerson.getName());
        Assert.assertEquals("nummer klopt niet","09645",testPerson.getGSMNummer());
        Assert.assertEquals("mail klopt niet","testmail",testPerson.getMail());

        testPerson.setMail("test2mail");
        Assert.assertEquals("naam klopt niet","testnaam",testPerson.getName());
        Assert.assertEquals("nummer klopt niet","09645",testPerson.getGSMNummer());
        Assert.assertEquals("mail klopt niet","test2mail",testPerson.getMail());

        testPerson.setGSMNummer("0965");
        Assert.assertEquals("naam klopt niet","testnaam",testPerson.getName());
        Assert.assertEquals("nummer klopt niet","0965",testPerson.getGSMNummer());
        Assert.assertEquals("mail klopt niet","test2mail",testPerson.getMail());

        testPerson.setName("anderenaam");
        Assert.assertEquals("naam klopt niet","anderenaam",testPerson.getName());
        Assert.assertEquals("nummer klopt niet","0965",testPerson.getGSMNummer());
        Assert.assertEquals("mail klopt niet","test2mail",testPerson.getMail());
    }

    @Test
    public void createPerson()
    {
        Person testPerson = new Person("testnaam","testmail","04964");
        Assert.assertEquals("naam klopt niet","testnaam",testPerson.getName());
        Assert.assertEquals("nummer klopt niet","04964",testPerson.getGSMNummer());
        Assert.assertEquals("mail klopt niet","testmail",testPerson.getMail());

        Person testPerson2 = new Person("testnaam");
        Assert.assertEquals("naam klopt niet","testnaam",testPerson2.getName());
        Assert.assertNull("nummer klopt niet", testPerson2.getGSMNummer());
        Assert.assertNull("mail klopt niet", testPerson2.getMail());

        testPerson2.setGSMNummer("096542");
        Assert.assertEquals("naam klopt niet","testnaam",testPerson2.getName());
        Assert.assertEquals("nummer klopt niet","096542",testPerson2.getGSMNummer());
        Assert.assertNull("mail klopt niet", testPerson2.getMail());

        testPerson2.setMail("test2mail");
        Assert.assertEquals("naam klopt niet","testnaam",testPerson2.getName());
        Assert.assertEquals("nummer klopt niet","096542",testPerson2.getGSMNummer());
        Assert.assertEquals("mail klopt niet","test2mail",testPerson2.getMail());
    }

    @Test
    public void testUUID()
    {
        Person testPerson = new Person("testnaam","testmail","04964");
        Person testPerson2 = new Person("testnaam");

        Assert.assertEquals("uuid klopt niet",UUID.class,testPerson.getId().getClass());
        Assert.assertEquals("uuid klopt niet",UUID.class,testPerson2.getId().getClass());

        UUID uuid_testPerson = testPerson.getId();
        UUID uuid_testPerson2 = testPerson2.getId();

        Assert.assertNotEquals("uuid klopt niet",uuid_testPerson,uuid_testPerson2);
    }

    @Test
    public void testToString()
    {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Person testPerson = new Person("testnaam","testmail","04964");
        Person testPerson2 = new Person("testnaam");

        outContent.reset();
        System.out.print(testPerson);
        assertEquals("the toString is not correct", "testnaam",outContent.toString());
        outContent.reset();

        System.out.print(testPerson2);
        assertEquals("the toString is not correct", "testnaam",outContent.toString());
        outContent.reset();

        testPerson2.setName("anderenaamnu");
        System.out.print(testPerson2);
        assertEquals("the toString is not correct", "anderenaamnu",outContent.toString());
        outContent.reset();

        System.setOut(System.out);




    }
}
