package com.example.lyloudinev2.admin.category.Terraza;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lyloudinev2.Mesa;
import com.example.lyloudinev2.MesaAdapter;
import com.example.lyloudinev2.MesaDAO;
import com.example.lyloudinev2.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class TerrazaFragment extends Fragment {

    private RecyclerView mesaRecyclerView;
    private MesaAdapter mesaAdapter;
    private MesaDAO mesaDAO;
    private View toggleContainer;
    private SearchView searchMesa;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_terraza, container, false);

        mesaRecyclerView = view.findViewById(R.id.mesa_recycler_view);
        toggleContainer = view.findViewById(R.id.toggle_container);
        searchMesa = view.findViewById(R.id.search_mesa);
        mesaDAO = new MesaDAO(getActivity());

        mesaRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        loadMesas("");

        searchMesa.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                loadMesas(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                loadMesas(newText);
                return true;
            }
        });

        FloatingActionButton addMesaButton = view.findViewById(R.id.btn_add_mesa);
        addMesaButton.setOnClickListener(v -> {
            mesaDAO.addMesa("Nueva Mesa", "Terraza", R.drawable.ic_mesa); // Agregar la mesa con el ícono
            loadMesas(searchMesa.getQuery().toString());
        });

        FloatingActionButton removeMesaButton = view.findViewById(R.id.btn_remove_mesa);
        removeMesaButton.setOnClickListener(v -> {
            List<Mesa> mesas = mesaDAO.getMesas("Terraza");
            if (!mesas.isEmpty()) {
                mesaDAO.deleteMesa(mesas.get(mesas.size() - 1).getId());
                loadMesas(searchMesa.getQuery().toString());
            }
        });

        FloatingActionButton toggleButton = view.findViewById(R.id.btn_toggle);
        toggleButton.setOnClickListener(v -> {
            if (toggleContainer.getVisibility() == View.GONE) {
                toggleContainer.setVisibility(View.VISIBLE);
                toggleButton.setImageResource(R.drawable.close); // Cambia el ícono al cerrarse
            } else {
                toggleContainer.setVisibility(View.GONE);
                toggleButton.setImageResource(R.drawable.ic_add); // Cambia el ícono al abrirse
            }
        });

        return view;
    }

    private void loadMesas(String query) {
        List<Mesa> mesas = mesaDAO.getMesas("Terraza");
        List<Mesa> filteredMesas = new ArrayList<>();
        for (Mesa mesa : mesas) {
            if (mesa.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredMesas.add(mesa);
            }
        }
        mesaAdapter = new MesaAdapter(getActivity(), filteredMesas);
        mesaRecyclerView.setAdapter(mesaAdapter);
    }

    @Override
    public void onDestroyView() {
        mesaDAO.close();
        super.onDestroyView();
    }
}
