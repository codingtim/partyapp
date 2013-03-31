package be.tim.partyapp.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import be.tim.partyapp.model.Person;

/**
 */
public class PeopleDaoDB implements PeopleDao {

    private SQLiteOpenHelper database;

    public PeopleDaoDB(SQLiteOpenHelper database) {
        this.database = database;
    }

    @Override
    public Person getPersonByNfcId(String nfcId) {
        SQLiteDatabase readableDatabase = database.getReadableDatabase();

        String sql = "select _id, name, nfcid from PEOPLE where nfcid = ?";
        Cursor cursor = readableDatabase.rawQuery(sql, new String[]{nfcId});

        boolean containsResult = cursor.moveToFirst();
        if(containsResult) {
            long id = cursor.getLong(0);
            String name = cursor.getString(1);
            return new Person(id, name, nfcId);
        }   else {
            return null;
        }
    }

    @Override
    public long addPerson(Person person) {
        SQLiteDatabase writableDatabase = database.getWritableDatabase();
        ContentValues contentValues = new ContentValues(2);
        contentValues.put("name", person.getName());
        contentValues.put("nfcid", person.getNfcId());
        long id = writableDatabase.insert("PEOPLE", null, contentValues);
        writableDatabase.close();
        return id;
    }
}
