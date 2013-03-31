package be.tim.partyapp.dao;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import be.tim.partyapp.database.PartyAppDatabase;
import be.tim.partyapp.model.Consumption;

import java.util.List;

/**
 */
public class ConsumptionDaoTest extends AndroidTestCase {

    private String TEST_FILE_PREFIX = "test_";
    private PartyAppDatabase partyAppDatabase;
    private ConsumptionDao consumptionDao;
    private String nfcId;


    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), TEST_FILE_PREFIX);

        partyAppDatabase = new PartyAppDatabase(context);
        consumptionDao = new ConsumptionDaoDB(partyAppDatabase);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetConsumptionByNfcId() throws Exception {
        List<Consumption> consumptions = consumptionDao.getConsumptionsForNfcId("lala");
        assertEquals(0, consumptions.size());
    }

    public void testAddConsumption() throws Exception {
        Consumption consumption = new Consumption("lala", "beer", "time");
        consumptionDao.addConsumption(consumption);
        List<Consumption> consumptions = consumptionDao.getConsumptionsForNfcId("lala");
        assertEquals(1, consumptions.size());
        assertEquals(consumption, consumptions.get(0));
    }

    public void testGetConsumptionWhereClause() throws Exception {
        Consumption consumption = new Consumption("lala", "beer", "time");
        consumptionDao.addConsumption(consumption);
        consumptionDao.addConsumption(new Consumption("papa", "beer", "time"));
        List<Consumption> consumptions = consumptionDao.getConsumptionsForNfcId("lala");
        assertEquals(1, consumptions.size());
        assertEquals(consumption, consumptions.get(0));
    }
}
