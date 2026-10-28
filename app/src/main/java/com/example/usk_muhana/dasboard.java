package com.example.usk_muhana;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

public class dasboard extends AppCompatActivity {

    ViewFlipper flipper;
    CardView tambah, history, logging;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dasboard);
        tambah = findViewById(R.id.tambah);
        history = findViewById(R.id.history);
        logging = findViewById(R.id.logging);
        flipper = findViewById(R.id.flipper);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(dasboard.this, tambah.class);
                startActivity(pindah);
            }

        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent history = new Intent(dasboard.this, list.class);
                startActivity(history);
            }

        });

        logging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent transaksi = new Intent(dasboard.this, list_transaksi.class);
                startActivity(transaksi);
            }

        });
        flipper.setInAnimation(this, android.R.anim.slide_in_left);
        flipper.setOutAnimation(this, android.R.anim.slide_out_right);

        // Otomatis beralih setiap 3 detik
        flipper.setAutoStart(true);
        flipper.setFlipInterval(3000);

        // Mulai animasi
        flipper.startFlipping();
    }

    // Panggil metode ini dengan onClick dari tombol atau metode lainnya
    public void nextView(View view) {
        flipper.showNext();
    }

    public void previousView(View view) {
        flipper.showPrevious();
    }
}
