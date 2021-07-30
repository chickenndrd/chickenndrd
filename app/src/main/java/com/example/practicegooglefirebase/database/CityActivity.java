package com.example.practicegooglefirebase.database;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.practicegooglefirebase.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CityActivity extends AppCompatActivity {

    Button btnAdd, btnUpdate, btnDelete, btnGet;
    ListView listView;
    ArrayList<City> arrayList;
    ArrayList<String> keys;
    ArrayAdapter arrayAdapter;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        db = FirebaseFirestore.getInstance();

        btnAdd = findViewById(R.id.button5);
        btnAdd.setOnClickListener(v -> {

            Map<String, String> data = new HashMap<>();
            data.put("name", "ABC");
            data.put("state", "CDE");

            City city = new City("Tan An", "LA");

            db.collection("cities").add(data)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(this, "OKE", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });

        btnUpdate = findViewById(R.id.button6);
        btnUpdate.setOnClickListener(v -> {

//            String id = "lBQIbghkeBnICHvOofQn";
//
//            Map<String, Object> data = new HashMap<>();
//            data.put("name", "Chau Thanh");
//            data.put("state", "TG");
//
//            db.collection("cities").document(id)
//                    .update(data)
//                    .addOnSuccessListener(documentReference -> {
//                        Toast.makeText(this, "OKE", Toast.LENGTH_SHORT).show();
//                    })
//                    .addOnFailureListener(e -> {
//                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                    });

            String id = keys.get(2);
            Toast.makeText(this, id, Toast.LENGTH_SHORT).show();

            Map<String, Object> data = new HashMap<>();
            data.put("name", "Chau Thanh");
            data.put("state", "TG");

            db.collection("cities").document(id)
                    .update(data)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(this, "OKE", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });

        btnDelete = findViewById(R.id.button7);
        btnDelete.setOnClickListener(v -> {

//            String id = "r2b8q8iPxXgBrjXkzdC6";
//
//            db.collection("cities").document(id)
//                    .delete()
//                    .addOnSuccessListener(documentReference -> {
//                        Toast.makeText(this, "OKE", Toast.LENGTH_SHORT).show();
//                    })
//                    .addOnFailureListener(e -> {
//                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                    });

            String id = keys.get(5);

            db.collection("cities").document(id)
                    .delete()
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(this, "OKE", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });

        btnGet = findViewById(R.id.button8);
        btnGet.setOnClickListener(v -> {

//            db.collection("cities")
//                    .get()
//                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                        @Override
//                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                            for(DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
//                                City city = documentSnapshot.toObject(City.class);
//                                arrayList.add(city);
//                            }
//                            arrayAdapter.notifyDataSetChanged();
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//
//                        }
//                    });

            db.collection("cities")
                    .whereEqualTo("state", "TG")
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for(DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                City city = documentSnapshot.toObject(City.class);
                                arrayList.add(city);
                            }
                            arrayAdapter.notifyDataSetChanged();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(CityActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        listView = findViewById(R.id.listview);
        arrayList = new ArrayList<>();
        keys = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(CityActivity.this,
                android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
        getDataRealTimeFireStore();
    }

    private void getDataRealTimeFireStore() {
        db.collection("cities").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for(DocumentChange documentChange : value.getDocumentChanges()){
                    DocumentSnapshot documentSnapshot = documentChange.getDocument();
                    switch (documentChange.getType()){
                        case ADDED:
                            keys.add(documentSnapshot.getId());
                            arrayList.add(documentSnapshot.toObject(City.class));
                            arrayAdapter.notifyDataSetChanged();
                            break;
                        case MODIFIED:
                            if(keys.indexOf(documentSnapshot.getId()) >= 0){
                                arrayList.set(keys.indexOf(documentSnapshot.getId()), documentSnapshot.toObject(City.class));
                            }
                            arrayAdapter.notifyDataSetChanged();
                            break;
                        case REMOVED:
                            if(keys.indexOf(documentSnapshot.getId()) >= 0){
                                arrayList.remove(keys.indexOf(documentSnapshot.getId()));
                                keys.remove(keys.indexOf(documentSnapshot.getId()));
                            }
                            arrayAdapter.notifyDataSetChanged();
                            break;
                    }
                }
            }
        });
    }
}