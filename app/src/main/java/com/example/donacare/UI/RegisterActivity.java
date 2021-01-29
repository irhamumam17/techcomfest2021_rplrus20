package com.example.donacare.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.donacare.Model.AccountModel;
import com.example.donacare.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {
    EditText txtUsername, txtName, txtPassword, txtEmail, txtPhone, txt_address;
    Button btnRegister;
    TextView tvLogin, txtForgotPassword;
    DatabaseReference databaseReference;
    boolean correctName, correctPassword, added;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tvLogin = findViewById(R.id.tvLogin);
        txtForgotPassword = findViewById(R.id.txt_forgot_register);
        btnRegister = findViewById(R.id.btnRegister);
        txtUsername = findViewById(R.id.txt_username_register);
        txtName = findViewById(R.id.txt_name_register);
        txtPassword = findViewById(R.id.txt_password_register);
        txtEmail = findViewById(R.id.txt_email_register);
        txt_address = findViewById(R.id.txt_address_register);
        txtPhone = findViewById(R.id.txt_phone_register);

        progressDialog = new ProgressDialog(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("users");

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctName = true;
                correctPassword = true;
                added = false;

                String mEmail = txtEmail.getText().toString();
                String mPassword = txtPassword.getText().toString();
                String mUsername = txtUsername.getText().toString();
                String mName = txtName.getText().toString();
                String mPhone = txtPhone.getText().toString().trim();
                String mAddress = txt_address.getText().toString();

                if (TextUtils.isEmpty(mEmail)) {
                    txtEmail.setError("Email tidak boleh kosong.");
                    return;
                }
                if (TextUtils.isEmpty(mPassword)) {
                    txtPassword.setError("Password tidak boleh kosong.");
                    return;
                }
                if (TextUtils.isEmpty(mUsername)) {
                    txtUsername.setError("Username tidak boleh kosong.");
                    return;
                }
                if (TextUtils.isEmpty(mUsername)) {
                    txtName.setError("Nama tidak boleh kosong.");
                    return;
                }
                if (TextUtils.isEmpty(mPhone)) {
                    txtPhone.setError("No. Telp tidak boleh kosong.");
                    return;
                }
                if (TextUtils.isEmpty(mAddress)) {
                    txtPhone.setError("Alamat tidak boleh kosong.");
                    return;
                }
                if (mPassword.length() < 6) {
                    txtPassword.setError("Password minimal 6 karakter.");
                    return;
                }

                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        progressDialog.setMessage("Loading...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();

                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            AccountModel accountModel = dataSnapshot.getValue(AccountModel.class);
                            if (accountModel.getUsername().equals(txtUsername.getText().toString())) {
                                correctName = false;
                                added = false;
                                txtUsername.setError("Username sudah terpakai");
                                break;
                            } else {
                                correctName = true;
                                txtUsername.setError(null);
                            }
                        }

                        if (txtPassword.getText().toString().length() >= 6) correctPassword = true;
                        else correctPassword = false;

                        if (correctName && correctPassword) {
                            txtUsername.setError(null);
                            txtName.setError(null);
                            txtPhone.setError(null);
                            txtEmail.setError(null);
                            txtPassword.setError(null);
                            txt_address.setError(null);

                            if (mName.trim().isEmpty() || mEmail.trim().isEmpty() || mPassword.trim().isEmpty() || mUsername.trim().isEmpty() || mPhone.trim().isEmpty() || mAddress.trim().isEmpty()) {
                                Toast.makeText(getApplicationContext(), "Isi semua field", Toast.LENGTH_SHORT).show();
                            } else {
                                added = true;
                                AccountModel accountModel = new AccountModel(mName, mUsername, mEmail, mPassword, mPhone, mAddress, "user");
                                databaseReference.child(mUsername).setValue(accountModel);
                                Toast.makeText(getApplicationContext(), "Berhasil didaftarkan!", Toast.LENGTH_SHORT).show();
                                txtPassword.setText("");
                                txtEmail.setText("");
                                txtName.setText("");
                                txtPhone.setText("");
                                txtUsername.setText("");
                                txt_address.setText("");
                                txtUsername.requestFocus();
                            }
                        }

                        progressDialog.dismiss();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        progressDialog.dismiss();
                    }
                });

//                fAuth.createUserWithEmailAndPassword(mEmail, mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isSuccessful()){
//                            Toast.makeText(RegisterActivity.this, "Akun telah dibuat.", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
//                        }else {
//                            Toast.makeText(RegisterActivity.this, "Error! Gagal membuat akun." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });
    }
}