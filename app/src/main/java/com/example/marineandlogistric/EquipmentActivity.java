package com.example.marineandlogistric;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class EquipmentActivity extends AppCompatActivity {

    RecyclerView recyclerViewEquipment;
    Button btnAddEquipment;
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
                String name = cursor.getString(2);
                String type = cursor.getString(3);
                String serial = cursor.getString(4);
                equipmentList.add(new Equipment(name, type, serial));
            }
            cursor.close();
        }
        adapter = new EquipmentAdapter(equipmentList);
        recyclerViewEquipment.setAdapter(adapter);
    }
}
