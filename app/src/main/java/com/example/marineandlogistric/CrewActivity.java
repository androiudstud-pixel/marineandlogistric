package com.example.marineandlogistric;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CrewActivity extends AppCompatActivity {

    RecyclerView recyclerViewCrew;
    View btnAddCrew;

    ArrayList<Crew> crewList;
    CrewAdapter crewAdapter;

    DatabaseHelper databaseHelper;

    int shipId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crew);

        shipId = getIntent().getIntExtra("shipId", -1);

        recyclerViewCrew = findViewById(R.id.recyclerViewCrew);
        btnAddCrew = findViewById(R.id.btnAddCrew);

        databaseHelper = new DatabaseHelper(this);

        recyclerViewCrew.setLayoutManager(new LinearLayoutManager(this));

        btnAddCrew.setOnClickListener(v -> {

            Intent intent = new Intent(CrewActivity.this, AddCrewActivity.class);
            intent.putExtra("shipId", shipId);
            startActivity(intent);

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadCrew();
    }

    private void loadCrew() {

        crewList = new ArrayList<>();

        Cursor cursor = databaseHelper.getAllCrew(shipId);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(2);
            String rank = cursor.getString(3);
            String nationality = cursor.getString(4);
            String passport = cursor.getString(5);

            crewList.add(new Crew(id, name, rank, nationality, passport));
        }

        cursor.close();

        crewAdapter = new CrewAdapter(crewList);
        recyclerViewCrew.setAdapter(crewAdapter);
    }
}