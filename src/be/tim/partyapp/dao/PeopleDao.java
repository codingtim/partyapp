package be.tim.partyapp.dao;

import be.tim.partyapp.model.Person;

/**
 */
public interface PeopleDao {
    Person getPersonByNfcId(String nfcId);

    long addPerson(Person person);
}
