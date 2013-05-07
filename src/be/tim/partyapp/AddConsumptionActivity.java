package be.tim.partyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import be.tim.partyapp.model.Consumption;
import be.tim.partyapp.model.ConsumptionType;
import be.tim.partyapp.model.Person;
import be.tim.partyapp.service.PeopleService;

import java.util.List;

public class AddConsumptionActivity extends Activity {

    private PeopleService peopleService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumptionadd);

        peopleService = ((PartyAppApplication) getApplication()).getPeopleService();

        Intent intent = getIntent();
        final long id = intent.getLongExtra("id", -1);
        final String name = intent.getStringExtra("name");
        final String nfcId = intent.getStringExtra("nfcId");

        Person person = new Person(id, name, nfcId);

        LinearLayout btnLayout = (LinearLayout) findViewById(R.id.btnLayout);

        for(ConsumptionType type: ConsumptionType.values()) {
            Button button = new Button(this);
            final String consumptionName = type.getName();
            button.setText(consumptionName);
            button.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        peopleService.addConsumption(nfcId, consumptionName);

                        Intent intent = new Intent(AddConsumptionActivity.this, PersonOverviewActivity.class);
                        intent.putExtra("nfcId", nfcId);
                        intent.putExtra("name", consumptionName);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    }
                    return true;
                }
            });
            btnLayout.addView(button);
        }

    }
}
