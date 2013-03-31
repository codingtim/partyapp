package be.tim.partyapp;

import android.app.Application;
import be.tim.partyapp.dao.ConsumptionDao;
import be.tim.partyapp.dao.ConsumptionDaoDB;
import be.tim.partyapp.dao.PeopleDao;
import be.tim.partyapp.dao.PeopleDaoDB;
import be.tim.partyapp.database.PartyAppDatabase;
import be.tim.partyapp.service.PeopleService;
import be.tim.partyapp.service.PeopleServiceImpl;

/**
 */
public class PartyAppApplication extends Application {

    private PeopleService peopleService;

    @Override
    public void onCreate() {
        super.onCreate();

        PartyAppDatabase database = new PartyAppDatabase(getApplicationContext());

        PeopleDao peopleDao = new PeopleDaoDB(database);
        ConsumptionDao consumptionDao = new ConsumptionDaoDB(database);
        peopleService = new PeopleServiceImpl(peopleDao, consumptionDao);
    }

    public PeopleService getPeopleService() {
        return peopleService;
    }
}
