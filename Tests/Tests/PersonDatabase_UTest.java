package Tests;

import be.uantwerpen.fti.Database.PersonDatabase;
import be.uantwerpen.fti.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import org.junit.Before;
import org.mockito.Mockito;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.UUID;


// Run with PowerMock, an extended version of Mockito
@RunWith(PowerMockRunner.class)
// Prepare class RegistrationController for testing by injecting mocks
@PrepareForTest(PersonDatabase.class)


public class PersonDatabase_UTest {

    public PersonDatabase_UTest()
    {

    }

    @Before
    public void initialize()
    {

    }

    @Test
    //@SuppressWarnings("unchecked")
    public void t_addEntry() throws NoSuchFieldException, IllegalAccessException
    {
        Field field = PersonDatabase.class.getDeclaredField("db");
        System.out.println(field.getType());
        System.out.println(field);
        field.setAccessible(true);

        PersonDatabase personDatabase_underTest = PersonDatabase.getInstance();
        HashMap<UUID, Person> mock_db = (HashMap<UUID, Person>) Mockito.mock(HashMap.class);
        field.set(personDatabase_underTest, mock_db);

        UUID mockid = UUID.randomUUID();
        Person mockPerson = Mockito.mock(Person.class);
        Mockito.when(mockPerson.getId()).thenReturn(mockid);


        personDatabase_underTest.addEntry(mockPerson);
        Mockito.verify(mock_db, Mockito.times(1)).put(mockid, mockPerson);

    }


    @Test
    public void t_getEntry_NoDefault() throws NoSuchFieldException, IllegalAccessException
    {
        Field field = PersonDatabase.class.getDeclaredField("db");
        field.setAccessible(true);

        PersonDatabase personDatabase_underTest = PersonDatabase.getInstance();
       // HashMap<UUID, Person> mock_db = (HashMap<UUID, Person>) Mockito.mock(HashMap.class);
       // field.set(personDatabase_underTest, mock_db);

        UUID mockid = UUID.randomUUID();
        Person mockPerson = Mockito.mock(Person.class);
        Mockito.when(mockPerson.getId()).thenReturn(mockid);

        personDatabase_underTest.addEntry(mockPerson);
        System.out.println("array");
        System.out.println(personDatabase_underTest.PersonArray());
        for (Person p : personDatabase_underTest.PersonArray()){
           System.out.println("personid");
           System.out.println(p.getId());
        }

        Person returnedperson = personDatabase_underTest.getEntry(mockid);
        System.out.println(returnedperson);
        Assert.assertEquals("Testing getEntry - should return mockObject", mockPerson, returnedperson);
    }
/*
    @Test
    public void t_getEntry_Default() throws Exception
    {
        Field field = RegistrationDB.class.getDeclaredField("db");
        field.setAccessible(true);

        Database registrationDB_underTest = RegistrationDB.getInstance();
        HashMap<Employee, RegisterEntry> mock_db = new HashMap<>();
        field.set(registrationDB_underTest, mock_db);

        Employee mockEmployee = Mockito.mock(Employee.class);
        // Make sure the constructor for RegisterEntryNull is being mocked
        RegisterEntryNull mockRegisterEntry = Mockito.mock(RegisterEntryNull.class);
        PowerMockito.whenNew(RegisterEntryNull.class).withNoArguments().thenReturn(mockRegisterEntry);

        RegisterEntry returnedEntry = registrationDB_underTest.getEntry(mockEmployee);
        Assert.assertEquals("Testing getEntry - should return mockObject", mockRegisterEntry, returnedEntry);
    }

 */

}

