package com.example.marineandlogistric;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddShipActivity extends AppCompatActivity {

    EditText etShipName, etIMO, etType, etFlag, etStatus;
    Button btnSaveShip;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ship);

        databaseHelper = new DatabaseHelper(this);

        etShipName = findViewById(R.id.etShipName);
        etIMO = findViewById(R.id.etIMO);
        etType = findViewById(R.id.etType);
        etFlag = findViewById(R.id.etFlag);
        etStatus = findViewById(R.id.etStatus);

        btnSaveShip = findViewById(R.id.btnSaveShip);

        btnSaveShip.setOnClickListener(v -> saveShip());
    }

    private void saveShip() {

        String name = etShipName.getText().toString().trim();
        String imo = etIMO.getText().toString().trim();
        String type = etType.getText().toString().trim();
        String flag = etFlag.getText().toString().trim();
        String status = etStatus.getText().toString().trim();

        if (TextUtils.isEmpty(name) ||
                TextUtils.isEmpty(imo) ||
                TextUtils.isEmpty(type) ||
                TextUtils.isEmpty(flag) ||
                TextUtils.isEmpty(status)) {

            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean inserted = databaseHelper.insertShip(
                name,
                imo,
                type,
                flag,
                status
        );

        if (inserted) {
            Toast.makeText(this, "Ship Added Successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {

            Toast.makeText(this,
                    "Failed to Add Ship",
                    Toast.LENGTH_SHORT).show();

        }
    }
}