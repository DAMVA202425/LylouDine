package com.example.lyloudinev2.admin;

<<<<<<< HEAD
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.lyloudinev2.R;
import com.example.lyloudinev2.admin.category.CategoriaFragment;
=======
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import com.example.lyloudinev2.R;

>>>>>>> 79e83847dc079fe72477831f712727a54114b9fb

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

<<<<<<< HEAD
        // Obtener el nombre de usuario desde SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "Usuario no encontrado");

        // Mostrar el nombre de usuario en el TextView
        TextView textViewUsername = findViewById(R.id.textView2);
        textViewUsername.setText(username);

=======
>>>>>>> 79e83847dc079fe72477831f712727a54114b9fb
        ImageButton btnClose = findViewById(R.id.b_Close);
        btnClose.setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
<<<<<<< HEAD

        Button btnDelivery = findViewById(R.id.btnDelivery);
        btnDelivery.setOnClickListener(v -> loadFragment(new DeliveryFragment()));

        Button btnCategoria = findViewById(R.id.btnCategoria);
        btnCategoria.setOnClickListener(v -> loadFragment(new CategoriaFragment()));

        Button btnPedidos = findViewById(R.id.btnPedidos);
        btnPedidos.setOnClickListener(v -> loadFragment(new PedidosFragment()));

        Button btnAcerca = findViewById(R.id.btnAcercade);
        btnAcerca.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, InfoActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
=======
>>>>>>> 79e83847dc079fe72477831f712727a54114b9fb
    }
}
