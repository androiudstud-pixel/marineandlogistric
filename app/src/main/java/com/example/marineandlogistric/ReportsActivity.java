package com.example.marineandlogistric;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ReportsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    View btnAdd;
    ArrayList<Report> list;
    ReportAdapter adapter;
    DatabaseHelper db;
    int shipId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        shipId = getIntent().getIntExtra("shipId", -1);
        recyclerView = findViewById(R.id.recyclerViewReports);
        btnAdd = findViewById(R.id.btnAddReport);
        db = new DatabaseHelper(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(ReportsActivity.this, AddReportActivity.class);
            intent.putExtra("shipId", shipId);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        list = new ArrayList<>();
        Cursor cursor = db.getAllReports(shipId);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String title = cursor.getString(2);
                String date = cursor.getString(3);
                String content = cursor.getString(4);
                list.add(new Report(title, date, content));
            }
            cursor.close();
        }
        adapter = new ReportAdapter(list);
        recyclerView.setAdapter(adapter);
    }
}
