package com.example.lyloudinev2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lyloudinev2.admin.AdminActivity;
import com.example.lyloudinev2.client.ClientActivity;
import com.example.lyloudinev2.employee.EmployeeActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText et_user;
    private EditText et_pass;
    private Button b_login;
    private TextView tv_error;
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);



        userManager = UserManager.getInstance(this);
        et_user = findViewById(R.id.ETUsername);
        et_pass = findViewById(R.id.ETPassword);
        b_login = findViewById(R.id.BLogin);
        tv_error = findViewById(R.id.TVError);


        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_user.getText().toString();
                String password = et_pass.getText().toString();
                if (userManager.authenticate(username, password)) {
                    String role = userManager.getRole(username);
                    if ("admin".equals(role)) {
                        tv_error.setVisibility(View.GONE);
                        startActivity(new Intent(LoginActivity.this, AdminActivity.class));
                        finish();
                    } else if ("employee".equals(role)) {
                        tv_error.setVisibility(View.GONE);
                        startActivity(new Intent(LoginActivity.this, EmployeeActivity.class));
                        finish();
                    } else if ("client".equals(role)) {
                        tv_error.setVisibility(View.GONE);
                        startActivity(new Intent(LoginActivity.this, ClientActivity.class));
                        finish();
                    }
                } else {
                    tv_error.setText("Credenciales incorrectas");
                    tv_error.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}