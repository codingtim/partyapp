package be.tim.partyapp.dao;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import be.tim.partyapp.database.PartyAppDatabase;
import be.tim.partyapp.model.Person;

/**
 */
public class PeopleDaoTest extends AndroidTestCase {

    private String TEST_FILE_PREFIX = "test_";
    private PartyAppDatabase partyAppDatabase;
    private PeopleDao peopleDao;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), TEST_FILE_PREFIX);

        partyAppDatabase = new PartyAppDatabase(context);
        peopleDao = new PeopleDaoDB(partyAppDatabase);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetPersonByNfcIdNull() throws Exception {
        Person person = peopleDao.getPersonByNfcId("lala");
        assertNull(person);
    }

    public void testInsertPerson() throws Exception {
        String nfcId = "e45sq12";
        Person person = new Person("Tim", nfcId);
        peopleDao.addPerson(person);
        Person retrievedPerson = peopleDao.getPersonByNfcId(nfcId);
        assertEquals(person, retrievedPerson);
    }
}
