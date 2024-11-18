package com.example.lyloudine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainLogin extends AppCompatActivity {

    private EditText et_user;
    private EditText et_pass;
    private Button b_login;
    private TextView tv_error;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_login);


        et_user = findViewById(R.id.ETUsername);
        et_pass = findViewById(R.id.ETPassword);
        b_login = findViewById(R.id.BLogin);
        tv_error = findViewById(R.id.TVError);


        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_user.getText().toString();
                String password = et_pass.getText().toString();

                if (username.equals("admin") && password.equals("admin")) {
                    // Acción a realizar cuando las credenciales son correctas
                    tv_error.setVisibility(View.GONE);
                    // Iniciar una nueva actividad o realizar otra acción aquí
                    Intent intent = new Intent(MainLogin.this, MainInicio.class);
                    startActivity(intent);
                } else {
                    // Acción a realizar cuando las credenciales son incorrectas
                    tv_error.setText("Nombre de usuario o contraseña incorrectos");
                    tv_error.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}