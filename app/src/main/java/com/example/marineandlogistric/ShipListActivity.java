package com.example.marineandlogistric;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShipListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ShipAdapter adapter;
    ArrayList<Ship> shipList;

    DatabaseHelper databaseHelper;

    Button btnAddShip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship_list);

        recyclerView = findViewById(R.id.recyclerViewShips);
        btnAddShip = findViewById(R.id.btnAddShip);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseHelper = new DatabaseHelper(this);

        shipList = new ArrayList<>();

        loadShips();

        adapter = new ShipAdapter(shipList);
        recyclerView.setAdapter(adapter);

        btnAddShip.setOnClickListener(v -> {
            Intent intent = new Intent(ShipListActivity.this, AddShipActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        shipList.clear();
        loadShips();
        adapter.notifyDataSetChanged();
    }

    private void loadShips() {

        Cursor cursor = databaseHelper.getAllShips();

        if (cursor.moveToFirst()) {

            do {

                String name = cursor.getString(1);
                String imo = cursor.getString(2);
                String type = cursor.getString(3);
                String flag = cursor.getString(4);
                String status = cursor.getString(5);

                shipList.add(new Ship(name, imo, type, flag, status));

            } while (cursor.moveToNext());
        }

        cursor.close();
    }
}