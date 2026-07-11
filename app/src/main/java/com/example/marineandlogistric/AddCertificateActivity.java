package com.example.marineandlogistric;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Locale;

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

        etIssue.setOnClickListener(v -> showDatePicker(etIssue));
        etIssue.setFocusable(false);

        etExpiry.setOnClickListener(v -> showDatePicker(etExpiry));
        etExpiry.setFocusable(false);

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

    private void showDatePicker(EditText editText) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, monthOfYear, dayOfMonth) -> {
                    String date = String.format(Locale.getDefault(), "%04d-%02d-%02d", year1, monthOfYear + 1, dayOfMonth);
                    editText.setText(date);
                }, year, month, day);
        datePickerDialog.show();
    }
}
