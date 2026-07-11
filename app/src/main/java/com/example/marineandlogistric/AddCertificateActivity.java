package com.example.marineandlogistric;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddCertificateActivity extends AppCompatActivity {

    EditText etName, etIssue, etExpiry;
    Button btnSave;
    DatabaseHelper db;
    int shipId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_certificate);

        shipId = getIntent().getIntExtra("shipId", -1);
        etName = findViewById(R.id.etCertName);
        etIssue = findViewById(R.id.etIssueDate);
        etExpiry = findViewById(R.id.etExpiryDate);
        btnSave = findViewById(R.id.btnSaveCertificate);
        db = new DatabaseHelper(this);

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String issue = etIssue.getText().toString().trim();
            String expiry = etExpiry.getText().toString().trim();

            if (name.isEmpty() || issue.isEmpty() || expiry.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean result = db.insertCertificate(shipId, name, issue, expiry);
            if (result) {
                Toast.makeText(this, "Certificate Added Successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to Add Certificate", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
