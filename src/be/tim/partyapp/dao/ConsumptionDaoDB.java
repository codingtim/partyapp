package be.tim.partyapp.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import be.tim.partyapp.database.PartyAppDatabase;
import be.tim.partyapp.model.Consumption;
import be.tim.partyapp.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class ConsumptionDaoDB implements ConsumptionDao {
    private final PartyAppDatabase database;

    public ConsumptionDaoDB(PartyAppDatabase database) {
        this.database = database;
    }

    @Override
    public List<Consumption> getConsumptionsForNfcId(String nfcId) {
        List<Consumption> consumptions = new ArrayList<Consumption>();
        SQLiteDatabase readableDatabase = database.getReadableDatabase();

        String sql = "select _id, type, mytimestamp from CONSUMPTION where nfcid = ?";
        Cursor cursor = readableDatabase.rawQuery(sql, new String[]{nfcId});

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Consumption consumption =  createConsumptionFromCursor(cursor, nfcId);
            consumptions.add(consumption);
            cursor.moveToNext();
        }

        cursor.close();
        readableDatabase.close();
        return consumptions;
    }

    private Consumption createConsumptionFromCursor(Cursor cursor, String nfcId) {
        long id = cursor.getLong(0);
        String type = cursor.getString(1);
        String timestamp = cursor.getString(2);
        return new Consumption(id, nfcId, type, timestamp);
    }

    @Override
    public long addConsumption(Consumption consumption) {
        SQLiteDatabase writableDatabase = database.getWritableDatabase();
        ContentValues contentValues = new ContentValues(3);
        contentValues.put("type", consumption.getType());
        contentValues.put("nfcid", consumption.getNfcId());
        contentValues.put("mytimestamp", consumption.getTimestamp());
        long id = writableDatabase.insert("CONSUMPTION", null, contentValues);
        writableDatabase.close();
        return id;
    }
}
