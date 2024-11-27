package com.example.lyloudinev2.admin;

import android.os.Bundle;
import android.view.View;

import com.example.lyloudinev2.admin.category.CategoriaFragment;
import com.google.android.material.button.MaterialButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.lyloudinev2.R;

public class AdminActivity extends AppCompatActivity {

    private MaterialButton btnCategoria, btnDelivery, btnPedidos, btnMenu;
    private MaterialButton selectedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        initializeViews();
        setupListeners();

        // Inicializar y seleccionar el botón por defecto
        initializeDefaultButton(btnCategoria);
    }

    private void initializeViews() {
        btnCategoria = findViewById(R.id.b_Categoria);
        btnDelivery = findViewById(R.id.b_Delivery);
        btnPedidos = findViewById(R.id.b_Pedidos);
        btnMenu = findViewById(R.id.b_Menu);
    }

    private void setupListeners() {
        View.OnClickListener buttonClickListener = v -> {
            if (selectedButton != null && selectedButton != v) {
                deselectButton(selectedButton);
            }

            selectedButton = (MaterialButton) v;
            selectButton(selectedButton);

            // Cargar el fragmento correspondiente al botón seleccionado
            loadFragmentForSelectedButton(selectedButton);
        };

        btnCategoria.setOnClickListener(buttonClickListener);
        btnDelivery.setOnClickListener(buttonClickListener);
        btnPedidos.setOnClickListener(buttonClickListener);
        btnMenu.setOnClickListener(buttonClickListener);
    }

    private void initializeDefaultButton(MaterialButton defaultButton) {
        selectedButton = defaultButton;
        selectButton(selectedButton);
        // Cargar el fragmento correspondiente al botón por defecto
        loadFragmentForSelectedButton(defaultButton);
    }

    private void deselectButton(MaterialButton button) {
        button.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.white));
        button.setIconTint(ContextCompat.getColorStateList(this, R.color.colorPrimary));
    }

    private void selectButton(MaterialButton button) {
        button.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorPrimary));
        button.setIconTint(ContextCompat.getColorStateList(this, R.color.white));
    }

    private void loadFragmentForSelectedButton(MaterialButton button) {
        Fragment fragment = null;
        if (button.getId() == R.id.b_Categoria) {
            fragment = new CategoriaFragment();
        } else if (button.getId() == R.id.b_Delivery) {
            fragment = new DeliveryFragment();
        } else if (button.getId() == R.id.b_Pedidos) {
            fragment = new PedidosFragment();
        } else if (button.getId() == R.id.b_Menu) {
            fragment = new MenuFragment();
        }
        if (fragment != null) {
            loadFragment(fragment);
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
