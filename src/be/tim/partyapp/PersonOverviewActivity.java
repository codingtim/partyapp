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
    private int beer;
    private int gp;
    private TextView txtTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person);

        peopleService = ((PartyAppApplication) getApplication()).getPeopleService();

        Intent intent = getIntent();
        long id = intent.getLongExtra("id", -1);
        String name = intent.getStringExtra("name");
        String nfcId = intent.getStringExtra("nfcId");

        Person person = new Person(id, name, nfcId);

        TextView txtNfcId = (TextView) findViewById(R.id.txtNfcId);
        TextView txtName = (TextView) findViewById(R.id.txtName);
        txtTotal = (TextView) findViewById(R.id.txtTotal);

        txtNfcId.setText(nfcId);
        txtName.setText(name);

        Button btnBeer = (Button) findViewById(R.id.btnAddBeer);
        Button btnGp = (Button) findViewById(R.id.btnAddGp);

        addTouchListener(btnBeer, "beer", nfcId);
        addTouchListener(btnGp, "gp", nfcId);


        List<Consumption> consumptions = peopleService.getConsumptionsByNfcId(nfcId);
        ListView listConsumptions = (ListView) findViewById(R.id.listconsumptions);
        adapter = new ArrayAdapter<Consumption>(this, android.R.layout.simple_list_item_1, consumptions);
        listConsumptions.setAdapter(adapter);

        calculateTotals(consumptions);
    }

    private void calculateTotals(List<Consumption> consumptions) {
        for(Consumption c: consumptions) {
            addConsumptionCount(c);
        }
        setTotalText();
    }

    private void addConsumptionCount(Consumption c) {
        if(c.getType().equals("beer")) {
            beer++;
        }
        if(c.getType().equals("gp")) {
            gp++;
        }
    }

    private void addTouchListener(Button button, final String type, final String nfcId) {
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Consumption consumption = peopleService.addConsumption(nfcId, type);
                    addConsumption(consumption);

                }
                return true;
            }
        });
    }

    private void addConsumption(Consumption consumption) {
        adapter.add(consumption);
        addConsumptionCount(consumption);
        setTotalText();
    }

    private void setTotalText() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(beer);
        if(beer == 1) {
            stringBuilder.append(" beer");
        } else {
            stringBuilder.append(" beers");
        }
        stringBuilder.append(" ").append(gp);
        if(gp == 1) {
            stringBuilder.append(" gp");
        } else {
            stringBuilder.append(" gps");
        }
        txtTotal.setText(stringBuilder.toString());
    }

}
