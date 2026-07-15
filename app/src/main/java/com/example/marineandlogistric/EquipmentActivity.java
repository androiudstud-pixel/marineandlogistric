package com.example.marineandlogistric;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class EquipmentActivity extends AppCompatActivity {

    RecyclerView recyclerViewEquipment;
    View btnAddEquipment;
    ArrayList<Equipment> equipmentList;
    EquipmentAdapter adapter;
    DatabaseHelper db;
    int shipId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);

        shipId = getIntent().getIntExtra("shipId", -1);
        recyclerViewEquipment = findViewById(R.id.recyclerViewEquipment);
        btnAddEquipment = findViewById(R.id.btnAddEquipment);
        db = new DatabaseHelper(this);

        recyclerViewEquipment.setLayoutManager(new LinearLayoutManager(this));

        btnAddEquipment.setOnClickListener(v -> {
            Intent intent = new Intent(EquipmentActivity.this, AddEquipmentActivity.class);
            intent.putExtra("shipId", shipId);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadEquipment();
    }

    private void loadEquipment() {
        equipmentList = new ArrayList<>();
        Cursor cursor = db.getAllEquipment(shipId);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(2);
                String type = cursor.getString(3);
                String serial = cursor.getString(4);
                equipmentList.add(new Equipment(id, name, type, serial));
            }
            cursor.close();
        }
        adapter = new EquipmentAdapter(equipmentList);
        recyclerViewEquipment.setAdapter(adapter);
    }
}
