package com.example.marineandlogistric;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    Button btnShips;
    Button btnCrew;
    Button btnEquipment;
    Button btnCertificates;
    Button btnReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnShips = findViewById(R.id.btnShips);
        btnCrew = findViewById(R.id.btnCrew);
        btnEquipment = findViewById(R.id.btnEquipment);
        btnCertificates = findViewById(R.id.btnCertificates);
        btnReports = findViewById(R.id.btnReports);

        // Open Ship Module
        btnShips.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, ShipListActivity.class);
            startActivity(intent);
        });

        // Coming Soon Modules
        btnCrew.setOnClickListener(v ->
                Toast.makeText(this, "Crew Module Coming Soon", Toast.LENGTH_SHORT).show());

        btnEquipment.setOnClickListener(v ->
                Toast.makeText(this, "Equipment Module Coming Soon", Toast.LENGTH_SHORT).show());

        btnCertificates.setOnClickListener(v ->
                Toast.makeText(this, "Certificates Module Coming Soon", Toast.LENGTH_SHORT).show());

        btnReports.setOnClickListener(v ->
                Toast.makeText(this, "Reports Module Coming Soon", Toast.LENGTH_SHORT).show());
    }
}