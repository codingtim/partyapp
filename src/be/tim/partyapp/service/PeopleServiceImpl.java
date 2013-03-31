package be.tim.partyapp.service;

import be.tim.partyapp.dao.ConsumptionDao;
import be.tim.partyapp.dao.PeopleDao;
import be.tim.partyapp.model.Consumption;
import be.tim.partyapp.model.Person;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;

/**
 */
public class PeopleServiceImpl implements PeopleService {

    private final PeopleDao peopleDao;
    private final ConsumptionDao consumptionDao;

    public PeopleServiceImpl(PeopleDao peopleDao, ConsumptionDao consumptionDao) {
        this.peopleDao = peopleDao;
        this.consumptionDao = consumptionDao;
    }

    @Override
    public Person getPersonByNfcId(String nfcId) {
        return peopleDao.getPersonByNfcId(nfcId);
    }

    @Override
    public long addPerson(String nfcId, String name) {
        Person person = new Person(name, nfcId);
        return peopleDao.addPerson(person);
    }

    @Override
    public Consumption addConsumption(String nfcId, String type) {
        DateTime dateTime = new DateTime();
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.mediumDateTime();
        Consumption consumption = new Consumption(nfcId, type, dateTimeFormatter.print(dateTime));
        long id= consumptionDao.addConsumption(consumption);
        consumption.setId(id);
        return consumption;
    }

    @Override
    public List<Consumption> getConsumptionsByNfcId(String nfcId) {
        return consumptionDao.getConsumptionsForNfcId(nfcId);
    }
}
