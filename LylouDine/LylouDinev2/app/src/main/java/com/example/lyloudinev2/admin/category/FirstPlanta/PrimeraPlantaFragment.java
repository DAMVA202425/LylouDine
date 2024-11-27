package com.example.lyloudinev2.admin.category.FirstPlanta;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lyloudinev2.Mesa;
import com.example.lyloudinev2.MesaDAO;
import com.example.lyloudinev2.R;

import java.util.List;

public class PrimeraPlantaFragment extends Fragment {

    private LinearLayout mesaContainer;
    private MesaDAO mesaDAO;
    private LinearLayout toggleContainer;
    private EditText searchMesa;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_primera_planta, container, false);

        mesaContainer = view.findViewById(R.id.mesa_container);
        toggleContainer = view.findViewById(R.id.toggle_container);
        searchMesa = view.findViewById(R.id.search_mesa);
        mesaDAO = new MesaDAO(getActivity());

        loadMesas("");

        searchMesa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                loadMesas(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        Button addMesaButton = view.findViewById(R.id.btn_add_mesa);
        addMesaButton.setOnClickListener(v -> {
            mesaDAO.addMesa("Nueva Mesa", "Primera Planta");
            loadMesas(searchMesa.getText().toString());
        });

        Button removeMesaButton = view.findViewById(R.id.btn_remove_mesa);
        removeMesaButton.setOnClickListener(v -> {
            List<Mesa> mesas = mesaDAO.getMesas("Primera Planta");
            if (!mesas.isEmpty()) {
                mesaDAO.deleteMesa(mesas.get(mesas.size() - 1).getId());
                loadMesas(searchMesa.getText().toString());
            }
        });

        Button toggleButton = view.findViewById(R.id.btn_toggle);
        toggleButton.setOnClickListener(v -> {
            if (toggleContainer.getVisibility() == View.GONE) {
                toggleContainer.setVisibility(View.VISIBLE);
            } else {
                toggleContainer.setVisibility(View.GONE);
            }
        });

        return view;
    }

    private void loadMesas(String query) {
        mesaContainer.removeAllViews();
        List<Mesa> mesas = mesaDAO.getMesas("Primera Planta");
        for (Mesa mesa : mesas) {
            if (mesa.getName().toLowerCase().contains(query.toLowerCase())) {
                Button mesaButton = new Button(getActivity());
                mesaButton.setText(mesa.getName());
                mesaButton.setOnClickListener(v -> {
                    // Acción al hacer clic en el botón de la mesa
                    // Podrías añadir la lógica para llevar a otra parte de la sección aquí
                });
                mesaContainer.addView(mesaButton);
            }
        }
    }

    @Override
    public void onDestroyView() {
        mesaDAO.close();
        super.onDestroyView();
    }
}
