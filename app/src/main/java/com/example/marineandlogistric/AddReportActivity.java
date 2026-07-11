package com.example.marineandlogistric;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
}
