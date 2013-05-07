package be.tim.partyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import be.tim.partyapp.model.Consumption;
import be.tim.partyapp.model.Person;
import be.tim.partyapp.service.PeopleService;

import java.util.List;

/**
 */
public class PersonOverviewActivity extends Activity {

    private PeopleService peopleService;
    private ArrayAdapter<Consumption> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person);

        peopleService = ((PartyAppApplication) getApplication()).getPeopleService();

        Intent intent = getIntent();
        final long id = intent.getLongExtra("id", -1);
        final String name = intent.getStringExtra("name");
        final String nfcId = intent.getStringExtra("nfcId");

        Person person = new Person(id, name, nfcId);

        TextView txtNfcId = (TextView) findViewById(R.id.txtNfcId);
        TextView txtName = (TextView) findViewById(R.id.txtName);

        txtNfcId.setText(nfcId);
        txtName.setText(name);

        Button btnAddConsumption = (Button) findViewById(R.id.btnAddConsumption);
        btnAddConsumption.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Intent intent = new Intent(PersonOverviewActivity.this, AddConsumptionActivity.class);
                    intent.putExtra("nfcId", nfcId);
                    intent.putExtra("name", name);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
                return true;
            }
        });

        List<Consumption> consumptions = peopleService.getConsumptionsByNfcId(nfcId);
        ListView listConsumptions = (ListView) findViewById(R.id.listconsumptions);
        adapter = new ArrayAdapter<Consumption>(this, android.R.layout.simple_list_item_1, consumptions);
        listConsumptions.setAdapter(adapter);

    }
}
