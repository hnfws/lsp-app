package com.example.usk_muhana;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database2 extends SQLiteOpenHelper {
    private static final String DATABASE_NAME ="transaksi.db";
    private static final int DATABASE_VERSION =1;

    public Database2(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String transaksi = "create table transaksi(nama_siswa text null, nama_operator text null, nama_kegiatan text null, harga text null, uang_bayar text null, kembalian text null);";
        Log.d("Data", "onCreate" +transaksi);
        db.execSQL(transaksi);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db0, int db1, int db2) {

    }
}