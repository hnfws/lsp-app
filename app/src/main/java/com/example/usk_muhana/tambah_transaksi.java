package com.example.usk_muhana;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class tambah_transaksi extends AppCompatActivity {

    protected Cursor cursor;
    Database2 database;
    EditText nama_siswa, nama_operator, nama_kegiatan, harga, uang_bayar;
    Button tambah_transaksi;
    String kembalian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_transaksi);
        database = new Database2(this);
        nama_siswa = findViewById(R.id.nama_siswa);
        nama_operator = findViewById(R.id.nama_operator);
        nama_kegiatan = findViewById(R.id.nama_kegiatan);
        harga = findViewById(R.id.harga);
        uang_bayar = findViewById(R.id.uang_bayar);
        tambah_transaksi = findViewById(R.id.tambah);

        tambah_transaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double uangBayar = Double.parseDouble(uang_bayar.getText().toString());
                    double hargaKegiatan = Double.parseDouble(harga.getText().toString());

                    double kembalian = uangBayar - hargaKegiatan;

                    SQLiteDatabase db = database.getWritableDatabase();
                    db.execSQL("insert into transaksi(nama_siswa, nama_operator, nama_kegiatan, harga, uang_bayar, kembalian) values('" +
                            nama_siswa.getText().toString() + "','" +
                            nama_operator.getText().toString() + "','" +
                            nama_kegiatan.getText().toString() + "', '"+
                            harga.getText().toString()+"','"+
                            uang_bayar.getText().toString()+"','"+
                            String.valueOf(kembalian) +"')");

                    Toast.makeText(tambah_transaksi.this, "data tersimpan", Toast.LENGTH_SHORT).show();
                    list_transaksi.ta.RefreshList();
                    finish();
                } catch (NumberFormatException e) {
                    // Tangani jika input tidak berupa angka
                    Toast.makeText(tambah_transaksi.this, "Masukkan angka yang valid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}