package com.example.donacare.UI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.donacare.Adapter.KelasAdapter;
import com.example.donacare.Model.KelasModel;
import com.example.donacare.R;

import java.util.ArrayList;

public class DetailKelasActivity extends AppCompatActivity {

    RecyclerView recyclerView_kelas;
    ArrayList<KelasModel> kelasModels;
    ArrayAdapter adapter;
    KelasAdapter kelasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kelas);

        Toolbar toolbar = findViewById(R.id.toolbar_detail_kelas);
        recyclerView_kelas = findViewById(R.id.rv_list_kelas);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Daftar Kelas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        kelasModels = new ArrayList<>();
        getData();
        adapter = new ArrayAdapter<KelasAdapter>(DetailKelasActivity.this, R.layout.support_simple_spinner_dropdown_item);

        kelasModels = new ArrayList<>();
        kelasModels.add(new KelasModel("1", "Kelas IPA VII", "Kelas pembelajaran mata pelajaran IPA untuk kelas VII(7 SMP)."));
        kelasModels.add(new KelasModel("2", "Kelas B.Indonesia V", "Kelas pembelajaran mata pelajaran Bahasa Indonesia untuk kelas V(5 SD)"));
        kelasModels.add(new KelasModel("3", "Kelas Art of Communication - Umum", "Kelas Art of Communication atau kelas public speaking yang diselenggarakan secara umum."));
        kelasModels.add(new KelasModel("4", "Kelas UI/UX Design - Umum", "Kelas UI/UX Design ."));
        kelasModels.add(new KelasModel("5", "Kelas Pemrograman Dasar - Umum", "Lorem ipsum dolor sit amet, consectetur adipiscing elit."));
        kelasModels.add(new KelasModel("6", "Kelas B.Inggris - Umum", "Lorem ipsum dolor sit amet, consectetur adipiscing elit."));

        kelasAdapter = new KelasAdapter(DetailKelasActivity.this, kelasModels);
        LinearLayoutManager layoutManager = new LinearLayoutManager(DetailKelasActivity.this);
        recyclerView_kelas.setLayoutManager(layoutManager);
        recyclerView_kelas.setAdapter(kelasAdapter);

        kelasAdapter.setOnItemClickListener(new KelasAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(DetailKelasActivity.this, DetailInfoKelasActivity.class);
                startActivity(intent);
            }
        });
    }
    private void getData() {
        kelasModels = new ArrayList<>();
    }
}