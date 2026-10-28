package com.example.usk_muhana;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class list_transaksi extends AppCompatActivity {

    String[] transaksi;
    Menu menu;
    protected Cursor cursor;
    Database2 database;
    public static list_transaksi ta;
    ListView list_transaksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_transaksi);

        list_transaksi = findViewById(R.id.list);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(list_transaksi.this, tambah_transaksi.class);
                startActivity(pindah);
            }

            });
        ta = this;
        database = new Database2(this);
        RefreshList();
        }


    public void RefreshList() {
        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT*FROM transaksi", null);
        transaksi = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            transaksi[i] = cursor.getString(0).toString();

        }
        list_transaksi.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, transaksi));
        list_transaksi.setSelected(true);

        list_transaksi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = transaksi[arg2];
                final CharSequence[] dialogitem = {"lihat detai transaksi", "hapus transaksi"};
                AlertDialog.Builder builder = new AlertDialog.Builder(list_transaksi.this);
                builder.setTitle("pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0:
                                Intent i = new Intent(getApplicationContext(), detail_transaksi.class);
                                i.putExtra("nama_siswa", selection);
                                startActivity(i);
                                break;

                            case 1:
                                SQLiteDatabase db = database.getWritableDatabase();
                                db.execSQL("delete from transaksi where nama_siswa='"+ selection +"'");
                                RefreshList();
                                break;
                        }

                    }
                });
                builder.create().show();

            }
        });
        ((ArrayAdapter) list_transaksi.getAdapter()).notifyDataSetInvalidated();


    }
}