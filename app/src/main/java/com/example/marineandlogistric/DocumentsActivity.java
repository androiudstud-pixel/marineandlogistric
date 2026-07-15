package com.example.marineandlogistric;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class DocumentsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    View btnAdd;
    ArrayList<Document> list;
    DocumentAdapter adapter;
    DatabaseHelper db;
    int shipId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents);

        shipId = getIntent().getIntExtra("shipId", -1);
        recyclerView = findViewById(R.id.recyclerViewDocuments);
        btnAdd = findViewById(R.id.btnAddDocument);
        db = new DatabaseHelper(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(DocumentsActivity.this, AddDocumentActivity.class);
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
        Cursor cursor = db.getAllDocuments(shipId);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String type = cursor.getString(2);
                String date = cursor.getString(3);
                list.add(new Document(id, name, type, date));
            }
            cursor.close();
        }
        adapter = new DocumentAdapter(list);
        recyclerView.setAdapter(adapter);
    }
}
