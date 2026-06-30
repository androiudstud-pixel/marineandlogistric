package com.example.marineandlogistric;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddCrewActivity extends AppCompatActivity {

    EditText etCrewName, etRank, etNationality, etPassport;
    Button btnSaveCrew;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_crew);

        etCrewName = findViewById(R.id.etCrewName);
        etRank = findViewById(R.id.etRank);
        etNationality = findViewById(R.id.etNationality);
        etPassport = findViewById(R.id.etPassport);

        btnSaveCrew = findViewById(R.id.btnSaveCrew);

        databaseHelper = new DatabaseHelper(this);

        btnSaveCrew.setOnClickListener(v -> {

            String name = etCrewName.getText().toString().trim();
            String rank = etRank.getText().toString().trim();
            String nationality = etNationality.getText().toString().trim();
            String passport = etPassport.getText().toString().trim();

            if (name.isEmpty() || rank.isEmpty()
                    || nationality.isEmpty() || passport.isEmpty()) {

                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean result = databaseHelper.insertCrew(
                    name,
                    rank,
                    nationality,
                    passport
            );

            if (result) {
                Toast.makeText(this, "Crew Added Successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to Save Crew", Toast.LENGTH_SHORT).show();
            }

        });
    }
}