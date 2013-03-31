package be.tim.partyapp.service;

import be.tim.partyapp.model.Consumption;
import be.tim.partyapp.model.Person;

import java.util.List;

/**
 */
public interface PeopleService {
    Person getPersonByNfcId(String nfcId);

    long addPerson(String nfcId, String name);

    Consumption addConsumption(String nfcId, String type);

    List<Consumption> getConsumptionsByNfcId(String nfcId);
}
