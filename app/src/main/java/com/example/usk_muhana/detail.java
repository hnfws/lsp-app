package com.example.usk_muhana;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class detail extends AppCompatActivity {

    protected Cursor cursor;
    Database database;
    TextView nama,kelas,jenkel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        nama = findViewById(R.id.nama);
        kelas = findViewById(R.id.kelas);
        jenkel = findViewById(R.id.jenkel);
        database = new Database(this);

        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT*FROM siswa WHERE nama='"+
                getIntent().getStringExtra("nama")+"'",null);
        cursor.moveToFirst();

        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            nama.setText(cursor.getString(0).toString());
            kelas.setText(cursor.getString(1).toString());
            jenkel.setText(cursor.getString(2).toString());

        }
    }
}