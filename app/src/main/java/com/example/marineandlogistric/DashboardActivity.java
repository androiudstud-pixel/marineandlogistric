package com.example.marineandlogistric;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    View btnShips, btnCrew, btnEquipment, btnCertificates, btnReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnShips = findViewById(R.id.btnShips);
        btnCrew = findViewById(R.id.btnCrew);
        btnEquipment = findViewById(R.id.btnEquipment);
        btnCertificates = findViewById(R.id.btnCertificates);
        btnReports = findViewById(R.id.btnReports);

        // Module Click Listeners
        btnShips.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, ShipListActivity.class);
            startActivity(intent);
        });

        btnCrew.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, CrewActivity.class);
            intent.putExtra("shipId", -1); // General view
            startActivity(intent);
        });

        btnEquipment.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, EquipmentActivity.class);
            intent.putExtra("shipId", -1); // General view
            startActivity(intent);
        });

        btnCertificates.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, CertificatesActivity.class);
            intent.putExtra("shipId", -1); // General view
            startActivity(intent);
        });

        btnReports.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, ReportsActivity.class);
            intent.putExtra("shipId", -1); // General view
            startActivity(intent);
        });
    }
}