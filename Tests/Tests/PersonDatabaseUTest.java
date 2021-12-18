package Tests;

import be.uantwerpen.fti.Database.PersonDatabase;
import be.uantwerpen.fti.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.UUID;


// Run with PowerMock, an extended version of Mockito
@RunWith(PowerMockRunner.class)
// Prepare class RegistrationController for testing by injecting mocks
@PrepareForTest(PersonDatabase.class)


public class PersonDatabaseUTest {

    public PersonDatabaseUTest() {
    }

    @Before
    public void initialize() {
    }

    @Test
    @SuppressWarnings("unchecked")
    public void t_addEntry() throws NoSuchFieldException, IllegalAccessException {
        Field field = PersonDatabase.class.getDeclaredField("db");
        field.setAccessible(true);

        PersonDatabase personDatabase_underTest = PersonDatabase.getInstance();
        HashMap<UUID, Person> mock_db = (HashMap<UUID, Person>) Mockito.mock(HashMap.class);
        field.set(personDatabase_underTest, mock_db);

        UUID mockid = UUID.randomUUID();
        Person mockPerson = Mockito.mock(Person.class);
        Mockito.when(mockPerson.getId()).thenReturn(mockid);


        personDatabase_underTest.addEntry(mockPerson);
        Mockito.verify(mock_db, Mockito.times(1)).put(mockid, mockPerson);
        personDatabase_underTest.removeEntry(mockPerson);

    }

}

