package com.example.donacare.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.donacare.Model.AccountModel;
import com.example.donacare.Preferences;
import com.example.donacare.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    EditText txt_username, txt_password;
    Button btnLogin;
    TextView tvRegister;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    boolean authUsername, authPassword;
    Preferences preferences;
    String email, name, address, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);
        txt_username = findViewById(R.id.txt_username_login);
        txt_password = findViewById(R.id.txt_password_login);
        tvRegister = findViewById(R.id.tvRegister);

        preferences = new Preferences();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("users");

        progressDialog = new ProgressDialog(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txt_username.getText().toString();
                String password = txt_password.getText().toString();
                boolean correctUsername = false;
                boolean correctPassword = false;
                authUsername = false;
                authPassword = false;

                if (username.trim().isEmpty()) {
                    txt_username.setError("Username tidak boleh kosong!");
                } else {
                    correctUsername = true;
                }
                if (password.isEmpty()) {
                    txt_password.setError("Password tidak boleh kosong!");
                } else if (password.length() < 6) {
                    txt_password.setError("Password harus 6 karakter atau lebih");
                } else {
                    correctPassword = true;
                }

                if (correctUsername && correctPassword) {
                    progressDialog.setMessage("Loading...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    txt_username.setError(null);
                    txt_password.setError(null);

                    databaseReference.child(username);
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            progressDialog.dismiss();
                            if (snapshot.getValue() != null) {
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    if (username.equals(dataSnapshot.getValue(AccountModel.class).getUsername())) {
                                        authUsername = true;
                                        name = dataSnapshot.getValue(AccountModel.class).getNama();
                                        email = dataSnapshot.getValue(AccountModel.class).getEmail();
                                        address = dataSnapshot.getValue(AccountModel.class).getAlamat();
                                        phone = dataSnapshot.getValue(AccountModel.class).getHp();
                                        Log.d("alamat", address);
                                    }
                                    if (password.equals(dataSnapshot.getValue(AccountModel.class).getPassword())) {
                                        authPassword = true;
                                    }
                                }
                                if (authUsername && authPassword) {
                                    preferences.setName(getApplicationContext(), name);
                                    preferences.setAddress(getApplicationContext(), address);
                                    preferences.setUsername(getApplicationContext(), username);
                                    preferences.setEmail(getApplicationContext(), email);
                                    preferences.setPhone(getApplicationContext(), phone);
                                    preferences.setStatus(getApplicationContext(), true);

                                    Log.d("email", email);
                                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Username atau password salah", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            progressDialog.dismiss();
                        }
                    });
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                finish();
            }
        });
    }
}