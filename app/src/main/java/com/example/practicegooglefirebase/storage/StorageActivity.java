package com.example.practicegooglefirebase.storage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.practicegooglefirebase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class StorageActivity extends AppCompatActivity {

    ImageView imageView;
    Button btnUpload, btnDownload;
    FirebaseStorage db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        db = FirebaseStorage.getInstance();

        imageView = findViewById(R.id.imageView);

        btnUpload = findViewById(R.id.button9);
        btnUpload.setOnClickListener(v -> {

            try {
                InputStream inputStream = getAssets().open("regos-kornyei-qaLN1zkJ0X8-unsplash.jpg");
                StorageReference storageReference = db.getReference().child("image1.jpg");
                storageReference.putStream(inputStream)
                        .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                Toast.makeText(StorageActivity.this, "Ok", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(StorageActivity.this, "error", Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnDownload = findViewById(R.id.button10);
        btnDownload.setOnClickListener(v -> {

            try {
                StorageReference data = db.getReference().child("donald_trump_1.jpg");
                File tmp = File.createTempFile("abc", "jpg");
                data.getFile(tmp)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Bitmap bitmap = BitmapFactory.decodeFile(tmp.getPath());
                                imageView.setImageBitmap(bitmap);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(StorageActivity.this, "error", Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(StorageActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

//            try {
//                StorageReference data = db.getReference().child("donald_trump_1.jpg");
//                File tmp = File.createTempFile("abc", "jpg");
//                data.getFile(tmp)
//                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//                            @Override
//                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                                Bitmap bitmap = BitmapFactory.decodeFile(tmp.getPath());
//                                imageView.setImageBitmap(bitmap);
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(StorageActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        });
//            } catch (IOException e) {
//                e.printStackTrace();
//                Toast.makeText(StorageActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
        });
    }
}