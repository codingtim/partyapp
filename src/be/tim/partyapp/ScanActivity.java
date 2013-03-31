package be.tim.partyapp;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import be.tim.partyapp.model.Person;
import be.tim.partyapp.nfc.NfcUtils;
import be.tim.partyapp.service.PeopleService;
import be.tim.partyapp.service.PeopleServiceImpl;

/**
 */
public class ScanActivity extends Activity {

    private TextView txtId;
    private PeopleService peopleService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan);

        peopleService = ((PartyAppApplication) getApplication()).getPeopleService();

        txtId = (TextView) findViewById(R.id.textId);
        Intent intent = getIntent();
        Log.e("DANGER ZONE", "intent= " + intent.getAction());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        Log.e("DANGER ZONE", "intent= " + intent.getAction());
        if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction())) {
            Log.e("DANGER ZONE", "DISCOVERED!");
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

            byte[] id = tag.getId();
            String nfcId = NfcUtils.getString(id);
            txtId.setText(nfcId);

            Person person = peopleService.getPersonByNfcId(nfcId);
            if (person == null) {
                Intent nextIntent = new Intent(ScanActivity.this, AddPersonActivity.class);
                nextIntent.putExtra("nfcId", nfcId);
                startActivity(nextIntent);
            } else {
                Intent nextIntent = new Intent(ScanActivity.this, PersonOverviewActivity.class);
                nextIntent.putExtra("nfcId", person.getNfcId());
                nextIntent.putExtra("name", person.getName());
                nextIntent.putExtra("id", person.getId());
                startActivity(nextIntent);
            }
        }
    }

}
