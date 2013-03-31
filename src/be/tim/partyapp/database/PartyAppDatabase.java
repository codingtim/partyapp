package be.tim.partyapp.database;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 */
public class PartyAppDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PartyAppDatabase";

    private final Context context;

    public PartyAppDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createDatabase(db);
    }

    private void createDatabase(SQLiteDatabase db) {
        AssetManager assetManager = getAssetManager();
        readCreateDatabaseFile(db, assetManager);
    }

    private void readCreateDatabaseFile(SQLiteDatabase db, AssetManager assetManager) {
        BufferedReader bufferedReader = null;
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open("create.txt");
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                db.execSQL(line);
            }
        } catch (IOException e) {
            Log.e("DATABASE ERROR", "Error while opening file!", e);
        } finally {
            try {
                bufferedReader.close();
                inputStream.close();
            }catch (IOException e) {
                Log.e("DATABASE ERROR", "Error while closing file!", e);
            }
        }
    }

    private AssetManager getAssetManager() {
        return context.getAssets();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
