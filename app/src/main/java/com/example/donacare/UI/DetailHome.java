package com.example.donacare.UI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.donacare.R;

public class DetailHome extends AppCompatActivity {

    Button btn_direct_whatsapp, btn_donasi_detailHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_home);

        btn_donasi_detailHome = findViewById(R.id.btn_donasi_sekarang);
        btn_direct_whatsapp = findViewById(R.id.btn_direct_whatsapp);
        btn_direct_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://api.whatsapp.com/send?phone=6289618778082&text=Halo%20CS%20"));
                startActivity(intent);
            }
        });

        btn_donasi_detailHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailHome.this, InputDonasiBarangActivity.class);
                startActivity(intent);
            }
        });

    }
}