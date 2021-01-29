package com.example.donacare.UI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.donacare.Model.AccountModel;
import com.example.donacare.Model.DanaModel;
import com.example.donacare.Model.ItemModel;
import com.example.donacare.Preferences;
import com.example.donacare.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InputDonasiDanaActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    Button btnKirim;
    EditText Nominal, NoRek, AtasNama;

    DatabaseReference databaseReference;
    Preferences preferences;
    String nominal, noRek, atasNama;

    Spinner spItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_dana);

        Toolbar toolbar = findViewById(R.id.toolbar_input_donasi_dana);
        preferences = new Preferences();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("users");

        progressDialog = new ProgressDialog(this);

        btnKirim = findViewById(R.id.btn_Kirim_Dana);
        Nominal = findViewById(R.id.et_nominal_inputDana);
        NoRek = findViewById(R.id.et_noRekening_inputDana);
        AtasNama = findViewById(R.id.et_atasNama_inputDana);
        spItem = findViewById(R.id.spPilihanDonasi);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Form Donasi Dana");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        String[] ITEMS = {"Dana", "Barang", "Jasa"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spItem.setAdapter(adapter);

        spItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
//                Intent intent = null;
                if (item.equals("Barang")) {
                    startActivity(new Intent(getApplicationContext(), InputDonasiBarangActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                } else if (item.equals("Jasa")) {
                    startActivity(new Intent(getApplicationContext(), InputDonasiJasaActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nominal = Nominal.getText().toString();
                noRek = NoRek.getText().toString();
                atasNama = AtasNama.getText().toString();

                if (nominal.trim().isEmpty()||noRek.trim().isEmpty()||atasNama.trim().isEmpty()) {
                    Toast.makeText(InputDonasiDanaActivity.this, "Kolom tidak boleh kosong1", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.setMessage("Sending your data...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    Nominal.setError(null);
                    NoRek.setError(null);
                    AtasNama.setError(null);

                    uploadDana();
                }
            }
        });
    }

    private void uploadDana() {
        DanaModel danaModel = new DanaModel(nominal, noRek, atasNama);
        databaseReference.push().setValue(danaModel);
        progressDialog.dismiss();

        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}