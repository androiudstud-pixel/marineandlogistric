package com.example.marineandlogistric;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ShipDetailsActivity extends AppCompatActivity {

    TextView txtShipName, txtIMO, txtType, txtFlag, txtStatus;

    Button btnCrew;
    Button btnEquipment;
    Button btnCertificates;
    Button btnDocuments;
    Button btnReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship_details);

        txtShipName = findViewById(R.id.txtShipName);
        txtIMO = findViewById(R.id.txtIMO);
        txtType = findViewById(R.id.txtType);
        txtFlag = findViewById(R.id.txtFlag);
        txtStatus = findViewById(R.id.txtStatus);

        btnCrew = findViewById(R.id.btnCrew);
        btnEquipment = findViewById(R.id.btnEquipment);
        btnCertificates = findViewById(R.id.btnCertificates);
        btnDocuments = findViewById(R.id.btnDocuments);
        btnReports = findViewById(R.id.btnReports);

        // Receive Ship Data
        String name = getIntent().getStringExtra("name");
        String imo = getIntent().getStringExtra("imo");
        String type = getIntent().getStringExtra("type");
        String flag = getIntent().getStringExtra("flag");
        String status = getIntent().getStringExtra("status");

        txtShipName.setText(name);
        txtIMO.setText("IMO : " + imo);
        txtType.setText("Type : " + type);
        txtFlag.setText("Flag : " + flag);
        txtStatus.setText("Status : " + status);

        btnCrew.setOnClickListener(v ->
        {

            Intent intent = new Intent(ShipDetailsActivity.this, CrewActivity.class);
            startActivity(intent);});

        btnEquipment.setOnClickListener(v ->
                Toast.makeText(this, "Equipment Module Coming Soon", Toast.LENGTH_SHORT).show());

        btnCertificates.setOnClickListener(v ->
                Toast.makeText(this, "Certificates Module Coming Soon", Toast.LENGTH_SHORT).show());

        btnDocuments.setOnClickListener(v ->
                Toast.makeText(this, "Documents Module Coming Soon", Toast.LENGTH_SHORT).show());

        btnReports.setOnClickListener(v ->
                Toast.makeText(this, "Reports Module Coming Soon", Toast.LENGTH_SHORT).show());
    }
}