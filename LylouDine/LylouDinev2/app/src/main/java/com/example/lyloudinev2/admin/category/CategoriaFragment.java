package com.example.lyloudinev2.admin.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.lyloudinev2.R;
import com.example.lyloudinev2.admin.category.FirstPlanta.PrimeraPlantaFragment;
import com.example.lyloudinev2.admin.category.SecondPlanta.SegundaPlantaFragment;
import com.example.lyloudinev2.admin.category.Terraza.TerrazaFragment;

public class CategoriaFragment extends Fragment {

    private Button btnPrimeraPlanta, btnSegundaPlanta, btnTerraza;
    private Button selectedButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categoria, container, false);

        initializeViews(view);
        setupListeners();

        // Seleccionar y cargar el fragmento del botón por defecto
        initializeDefaultButton(btnPrimeraPlanta);

        return view;
    }

    private void initializeViews(View view) {
        btnPrimeraPlanta = view.findViewById(R.id.b_PPlanta);
        btnSegundaPlanta = view.findViewById(R.id.b_SPlanta);
        btnTerraza = view.findViewById(R.id.b_Terraza);
    }

    private void setupListeners() {
        View.OnClickListener buttonClickListener = v -> {
            if (selectedButton != null && selectedButton != v) {
                deselectButton(selectedButton);
            }

            selectedButton = (Button) v;
            selectButton(selectedButton);

            // Cargar el fragmento correspondiente al botón seleccionado
            loadFragmentForSelectedButton(selectedButton);
        };

        btnPrimeraPlanta.setOnClickListener(buttonClickListener);
        btnSegundaPlanta.setOnClickListener(buttonClickListener);
        btnTerraza.setOnClickListener(buttonClickListener);
    }

    private void initializeDefaultButton(Button defaultButton) {
        selectedButton = defaultButton;
        selectButton(selectedButton);
        // Cargar el fragmento correspondiente al botón por defecto
        loadFragmentForSelectedButton(defaultButton);
    }

    private void deselectButton(Button button) {
        button.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.colorAccent));
    }

    private void selectButton(Button button) {
        button.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.colorPrimary));
    }

    private void loadFragmentForSelectedButton(Button button) {
        Fragment fragment = null;
        if (button.getId() == R.id.b_PPlanta) {
            fragment = new PrimeraPlantaFragment();
        } else if (button.getId() == R.id.b_SPlanta) {
            fragment = new SegundaPlantaFragment();
        } else if (button.getId() == R.id.b_Terraza) {
            fragment = new TerrazaFragment();
        }
        if (fragment != null) {
            loadFragment(fragment);
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.child_fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
