package com.example.lyloudine;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainInicio extends AppCompatActivity {

    private Button btnCategoria, btnDelivery, btnPedidos, btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_inicio);

        initializeViews();
        setupListeners();

        // Cargar el fragmento inicial (Categoría por defecto)
        loadFragment(new CategoriaFragment());
    }

    private void initializeViews() {
        btnCategoria = findViewById(R.id.b_Categoria);
        btnDelivery = findViewById(R.id.b_Delivery);
        btnPedidos = findViewById(R.id.b_Pedidos);
        btnMenu = findViewById(R.id.b_Menu);
    }

    private void setupListeners() {
        btnCategoria.setOnClickListener(v -> loadFragment(new CategoriaFragment()));
        btnDelivery.setOnClickListener(v -> loadFragment(new DeliveryFragment()));
        btnPedidos.setOnClickListener(v -> loadFragment(new PedidosFragment()));
        btnMenu.setOnClickListener(v -> loadFragment(new MenuFragment()));
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
