package com.example.usk_muhana;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class detail_transaksi extends AppCompatActivity {

    protected Cursor cursor;
    Database2 database;
    TextView nama_siswa, nama_operator, nama_kegiatan, harga, uang_bayar, kembalian;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaksi);
        nama_siswa = findViewById(R.id.nama_siswa);
        nama_operator = findViewById(R.id.nama_operator);
        nama_kegiatan = findViewById(R.id.nama_kegiatan);
        harga = findViewById(R.id.harga);
        uang_bayar = findViewById(R.id.uang_bayar);
        kembalian = findViewById(R.id.kembalian);
        database = new Database2(this);

        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT*FROM transaksi WHERE nama_siswa='"+
                getIntent().getStringExtra("nama_siswa")+"'",null);
        cursor.moveToFirst();

        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            nama_siswa.setText(cursor.getString(0).toString());
            nama_operator.setText(cursor.getString(1).toString());
            nama_kegiatan.setText(cursor.getString(2).toString());
            harga.setText(cursor.getString(3).toString());
            uang_bayar.setText(cursor.getString(4).toString());
            kembalian.setText(cursor.getString(5).toString());

        }
    }
}