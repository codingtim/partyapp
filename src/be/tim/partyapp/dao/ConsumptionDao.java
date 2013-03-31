package be.tim.partyapp.dao;

import be.tim.partyapp.model.Consumption;

import java.util.List;

/**
 */
public interface ConsumptionDao {
    long addConsumption(Consumption consumption);

    List<Consumption> getConsumptionsForNfcId(String nfcId);
}
