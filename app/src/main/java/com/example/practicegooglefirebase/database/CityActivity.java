package com.example.practicegooglefirebase.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.practicegooglefirebase.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class CityActivity extends AppCompatActivity {

    Button btnAdd, btnUpdate, btnDelete;
    ListView listView;
    ArrayList<City> arrayList;
    ArrayAdapter arrayAdapter;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        db = FirebaseFirestore.getInstance();

        btnAdd = findViewById(R.id.button5);
        btnAdd.setOnClickListener(v -> {
            City city = new City("Tan An", "LA");
            db
        });

        btnUpdate = findViewById(R.id.button6);
        btnUpdate.setOnClickListener(v -> {

        });

        btnDelete = findViewById(R.id.button7);
        btnDelete.setOnClickListener(v -> {

        });

        listView = findViewById(R.id.listview);
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(CityActivity.this,
                android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
    }
}