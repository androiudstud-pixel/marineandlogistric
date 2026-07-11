package com.example.marineandlogistric;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddEquipmentActivity extends AppCompatActivity {

    EditText etName, etType, etSerial;
    Button btnSave;
    DatabaseHelper db;
    int shipId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_equipment);

        shipId = getIntent().getIntExtra("shipId", -1);
        etName = findViewById(R.id.etEqName);
        etType = findViewById(R.id.etEqType);
        etSerial = findViewById(R.id.etEqSerial);
        btnSave = findViewById(R.id.btnSaveEquipment);
        db = new DatabaseHelper(this);

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String type = etType.getText().toString().trim();
            String serial = etSerial.getText().toString().trim();

            if (name.isEmpty() || type.isEmpty() || serial.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean result = db.insertEquipment(shipId, name, type, serial);
            if (result) {
                Toast.makeText(this, "Equipment Added Successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to Add Equipment", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
