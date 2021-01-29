package com.example.donacare.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.donacare.R;

public class DonasiJasaActivity extends AppCompatActivity {

    Button btn_daftar_donasi_jasa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donasi_jasa);

        Toolbar toolbar = findViewById(R.id.toolbar_syarat_donasi_jasa);
        btn_daftar_donasi_jasa = findViewById(R.id.btn_daftar_donasi_jasa);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Donasi Jasa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_daftar_donasi_jasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonasiJasaActivity.this, InputDonasiJasaActivity.class);
                startActivity(intent);
            }
        });
    }
}