package com.example.marineandlogistric;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Locale;

public class AddReportActivity extends AppCompatActivity {

    EditText etReportTitle, etReportDate, etReportContent;
    Button btnSaveReport;

    DatabaseHelper databaseHelper;
    int shipId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report);

        shipId = getIntent().getIntExtra("shipId", -1);

        etReportTitle = findViewById(R.id.etReportTitle);
        etReportDate = findViewById(R.id.etReportDate);
        etReportContent = findViewById(R.id.etReportContent);
        btnSaveReport = findViewById(R.id.btnSaveReport);

        databaseHelper = new DatabaseHelper(this);

        etReportDate.setOnClickListener(v -> showDatePicker(etReportDate));
        etReportDate.setFocusable(false); // Prevent keyboard from opening

        btnSaveReport.setOnClickListener(v -> {
            String title = etReportTitle.getText().toString().trim();
            String date = etReportDate.getText().toString().trim();
            String content = etReportContent.getText().toString().trim();

            if (title.isEmpty() || date.isEmpty() || content.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean result = databaseHelper.insertReport(shipId, title, date, content);

            if (result) {
                Toast.makeText(this, "Report Added Successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to Add Report", Toast.LENGTH_SHORT).show();
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
