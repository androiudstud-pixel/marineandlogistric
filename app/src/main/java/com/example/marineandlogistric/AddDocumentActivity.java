package com.example.marineandlogistric;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import java.util.Locale;

public class AddDocumentActivity extends AppCompatActivity {

    EditText etName, etType, etDate;
    Button btnSave;
    DatabaseHelper db;
    int shipId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_document);

        shipId = getIntent().getIntExtra("shipId", -1);
        etName = findViewById(R.id.etDocName);
        etType = findViewById(R.id.etDocType);
        etDate = findViewById(R.id.etDocDate);
        btnSave = findViewById(R.id.btnSaveDocument);
        db = new DatabaseHelper(this);

        etDate.setOnClickListener(v -> showDatePicker(etDate));

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String type = etType.getText().toString().trim();
            String date = etDate.getText().toString().trim();

            if (name.isEmpty() || type.isEmpty() || date.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean result = db.insertDocument(shipId, name, type, date);
            if (result) {
                Toast.makeText(this, "Document Saved Successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to Save Document", Toast.LENGTH_SHORT).show();
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
