package com.example.usk_muhana;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class tambah extends AppCompatActivity {

    protected Cursor cursor;
    Database database;
    Button btn_simpan;
    EditText nama,kelas,jenkel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        database = new Database(this);
        nama = findViewById(R.id.nama);
        kelas = findViewById(R.id.kelas);
        jenkel = findViewById(R.id.jenkel);
        btn_simpan = findViewById(R.id.tambah);
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("insert into siswa(nama,kelas,jenkel) values('"+
                        nama.getText().toString() +"','"+
                        kelas.getText().toString() +"','"+
                        jenkel.getText().toString() +"')");
                Toast.makeText(tambah.this, "data tersimpan", Toast.LENGTH_SHORT).show();
                list.ma.RefreshList();
                finish();
            }
        });
    }
}