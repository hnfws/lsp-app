package com.example.usk_muhana;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class edit extends AppCompatActivity {

    protected Cursor cursor;
    Database database;
    EditText nama, kelas, jenkel;
    Button edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        nama = findViewById(R.id.nama);
        kelas = findViewById(R.id.kelas);
        jenkel = findViewById(R.id.jenkel);
        edit = findViewById(R.id.edit);
        database = new Database(this);

        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT*FROM siswa WHERE nama='"+
                getIntent().getStringExtra("nama")+"'", null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            nama.setText(cursor.getString(0).toString());
            kelas.setText(cursor.getString(1).toString());
            jenkel.setText(cursor.getString(2).toString());
        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("update siswa set nama='"+
                        nama.getText().toString() + "',kelas='" +
                        kelas.getText().toString() + "',jenkel='" +
                        jenkel.getText().toString() + "' WHERE nama='"+
                        getIntent().getStringExtra("nama")+"'");
                Toast.makeText(edit.this, "data telah diubah", Toast.LENGTH_SHORT).show();
                list.ma.RefreshList();
                finish();
            }
        });
    }
}
