package com.example.donacare.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.donacare.Preferences;
import com.example.donacare.R;

public class SplashActivity extends AppCompatActivity {

    Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        preferences = new Preferences();
        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 3 seconds
                    sleep(2 * 1000);

                    // After 5 seconds redirect to another intent
                    Intent i;
                    if (preferences.getStatus(getApplicationContext())) {
                        i = new Intent(getApplicationContext(), HomeActivity.class);
                    } else {
                        i = new Intent(getApplicationContext(), LoginActivity.class);
                    }
                    startActivity(i);

                    //Remove activity
                    finish();
                } catch (Exception e) {
                }
            }
        };
        // start thread
        background.start();
    }
}