package com.example.donacare.UI;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.donacare.Model.ItemModel;
import com.example.donacare.Preferences;
import com.example.donacare.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class InputDonasiBarangActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    String id;
    Spinner spItem;
    Button btn_inputFoto, btn_kirim;
    EditText txtJenisBarang, txtJumlahBarang, txtBeratBarang, txtAlamatLengkap;
    ProgressDialog progressDialog;
    Preferences preferences;

    DatabaseReference databaseReference;
    FirebaseStorage storage;
    StorageReference storageReference;
    StorageReference fileReference;

    final int IMG_REQUEST = 1000;
    Uri imageUri, downloadUri;
    String imageUrl, imageName, jenis, jumlah, berat, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_donasi_barang);
        btn_inputFoto= findViewById(R.id.btn_inputFoto_inputDonasi);


        final String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(this, permissions)) {
            EasyPermissions.requestPermissions(this, "Grant the permission to fully access this apps", 10, permissions);
        }

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference("items");
        databaseReference = FirebaseDatabase.getInstance().getReference("items");

        spItem = findViewById(R.id.spPilihanDonasi);
        btn_inputFoto = findViewById(R.id.btn_inputFoto_inputDonasi);
        btn_kirim = findViewById(R.id.btnKirim);
        txtJenisBarang = findViewById(R.id.txtJenisBarang);
        txtJumlahBarang = findViewById(R.id.txtJumlahBarang);
        txtBeratBarang = findViewById(R.id.txtBeratBarang);
        txtAlamatLengkap = findViewById(R.id.txtAlamatLengkap);

        Toolbar toolbar = findViewById(R.id.toolbarInput_Barang);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Form Donasi Barang");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        preferences = new Preferences();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("items");

        progressDialog = new ProgressDialog(this);

        btn_inputFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        String[] ITEMS = {"Barang", "Dana", "Jasa"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spItem.setAdapter(adapter);

        spItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
//                Intent intent = null;
                if (item.equals("Dana")) {
                    startActivity(new Intent(getApplicationContext(), InputDonasiDanaActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

                } else if (item.equals("Jasa")) {
                    startActivity(new Intent(getApplicationContext(), InputDonasiJasaActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btn_kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jenis = txtJenisBarang.getText().toString();
                berat = txtBeratBarang.getText().toString();
                jumlah = txtJumlahBarang.getText().toString();
                alamat = txtAlamatLengkap.getText().toString();

                if (jenis.trim().isEmpty() || jumlah.trim().isEmpty() || berat.trim().isEmpty() || alamat.trim().isEmpty() || imageUrl.trim().isEmpty()) {
                    Toast.makeText(InputDonasiBarangActivity.this, "Kolom tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.setMessage("Sending your data...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    imageName = Long.toString(System.currentTimeMillis());

                    fileReference = storageReference.child(imageName + "." + getFileExtension(imageUri));
                    fileReference.putFile(imageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()) {
                                throw task.getException();
                            }
                            return fileReference.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                downloadUri = task.getResult();
                                imageUrl = downloadUri.toString();
                                startActivity(new Intent(InputDonasiBarangActivity.this, HomeActivity.class));
                                uploadDonasi();
                            } else {
                                Toast.makeText(getApplicationContext(), "Edit failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private void uploadDonasi() {
        ItemModel itemModel = new ItemModel(jenis, jumlah, berat, alamat, imageUrl);
        databaseReference.push().setValue(itemModel);
        progressDialog.dismiss();
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            imageUrl = data.getDataString();
        }
    }



    // Request Permissions
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
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