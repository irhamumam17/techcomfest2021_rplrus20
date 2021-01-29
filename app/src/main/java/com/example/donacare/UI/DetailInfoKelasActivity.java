package com.example.donacare.UI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.donacare.R;

public class DetailInfoKelasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_info_kelas);

        Toolbar toolbar = findViewById(R.id.toolbar_detail_info_kelas);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Informasi Kelas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}