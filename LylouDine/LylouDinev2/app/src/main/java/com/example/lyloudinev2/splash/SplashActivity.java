package com.example.lyloudinev2.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lyloudinev2.LoginActivity;
import com.example.lyloudinev2.R;
import com.example.lyloudinev2.UserManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        UserManager.getInstance(this);

        // Mostrar la pantalla de inicio durante 3 segundos y luego iniciar la actividad principal
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000); // 3000 ms = 3 segundos
    }
}
