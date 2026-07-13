package com.example.marineandlogistric;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CertificatesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    View btnAdd;
    ArrayList<Certificate> list;
    CertificateAdapter adapter;
    DatabaseHelper db;
    int shipId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificates);

        shipId = getIntent().getIntExtra("shipId", -1);
        recyclerView = findViewById(R.id.recyclerViewCertificates);
        btnAdd = findViewById(R.id.btnAddCertificate);
        db = new DatabaseHelper(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(CertificatesActivity.this, AddCertificateActivity.class);
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
        Cursor cursor = db.getAllCertificates(shipId);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(2);
                String issue = cursor.getString(3);
                String expiry = cursor.getString(4);
                list.add(new Certificate(name, issue, expiry));
            }
            cursor.close();
        }
        adapter = new CertificateAdapter(list);
        recyclerView.setAdapter(adapter);
    }
}
