package Tests;

import be.uantwerpen.fti.Database.PersonDatabase;
import be.uantwerpen.fti.Person;
import be.uantwerpen.fti.observers.PersonDatabaseObserver;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import org.mockito.Mockito;

import java.util.UUID;


// Run with PowerMock, an extended version of Mockito
@RunWith(PowerMockRunner.class)
// Prepare class RegistrationController for testing by injecting mocks
@PrepareForTest(PersonDatabase.class)

public class PersonDatabaseUTestb {
    public PersonDatabaseUTestb() {
    }

    @Test
    public void A_getEntry_NoDefault()
    {
        //Field field = PersonDatabase.class.getDeclaredField("db");
        //field.setAccessible(true);

        PersonDatabase personDatabase_underTest = PersonDatabase.getInstance();
        personDatabase_underTest.addObserver(new PersonDatabaseObserver());
        //HashMap<UUID, Person> mock_db = new HashMap<>();
        //field.set(personDatabase_underTest, mock_db);

        //UUID mockid = UUID.randomUUID();
        String uuidAsString = "8b97d8bc-a2f3-43ce-8e53-0330446ba510";
        UUID mockid = UUID.fromString(uuidAsString);
        Person mockPerson = Mockito.mock(Person.class);
        Mockito.when(mockPerson.getId()).thenReturn(mockid);
        personDatabase_underTest.addEntry(mockPerson);

        Person returnedperson = personDatabase_underTest.getEntry(mockid);
        Assert.assertEquals("Testing getEntry - should return mockObject", mockPerson, returnedperson);
        personDatabase_underTest.removeEntry(mockPerson);
    }
}
