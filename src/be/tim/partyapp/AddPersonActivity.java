package be.tim.partyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import be.tim.partyapp.service.PeopleService;

/**
 */
public class AddPersonActivity extends Activity {

    private PeopleService peopleService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personadd);

        Intent intent = getIntent();
        final String nfcId = intent.getStringExtra("nfcId");

        peopleService = ((PartyAppApplication) getApplication()).getPeopleService();

        EditText editNfcId = (EditText) findViewById(R.id.editNfcId);
        final EditText editName = (EditText) findViewById(R.id.editName);
        Button btnSave = (Button) findViewById(R.id.btnSave);
        editNfcId.setText(nfcId);

        btnSave.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    String name = editName.getText().toString();
                    if (!"".equals(name)) {
                        long id = peopleService.addPerson(nfcId, name);
                        Intent intent = new Intent(AddPersonActivity.this, PersonOverviewActivity.class);
                        intent.putExtra("nfcId", nfcId);
                        intent.putExtra("name", name);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    }
                }
                return true;
            }
        });
    }

}
