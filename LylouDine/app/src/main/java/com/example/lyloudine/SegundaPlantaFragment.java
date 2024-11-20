package com.example.lyloudine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class SegundaPlantaFragment extends Fragment {

    private RecyclerView r_view;
    private MesaAdapter mesaAdapter;
    private SearchView s_view;
    private FloatingActionButton fabMain, fabAdd, fabRemove;
    private boolean isFabOpen = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_primera_planta, container, false);

        initializeViews(view);
        setupRecyclerView(view);
        setupSearchView();
        setupFabMenu();

        return view;
    }

    private void initializeViews(View view) {
        r_view = view.findViewById(R.id.r_view);
        s_view = view.findViewById(R.id.s_view);
        fabMain = view.findViewById(R.id.fab_main);
        fabAdd = view.findViewById(R.id.fab_add);
        fabRemove = view.findViewById(R.id.fab_remove);
    }

    private void setupRecyclerView(View view) {
        // Configurar LayoutManager y adaptador del RecyclerView
        r_view.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        mesaAdapter = new MesaAdapter(getMesas());
        r_view.setAdapter(mesaAdapter);

        // Agregar decoración de espacio al RecyclerView
        int space = getResources().getDimensionPixelSize(R.dimen.recycler_item_space);
        r_view.addItemDecoration(new SpaceItemDecoration(space));
    }

    private void setupSearchView() {
        // Configurar el listener de la SearchView para filtrar las mesas
        s_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mesaAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void setupFabMenu() {
        fabMain.setOnClickListener(v -> {
            if (isFabOpen) {
                closeFabMenu();
            } else {
                openFabMenu();
            }
        });

        fabAdd.setOnClickListener(v -> {
            mesaAdapter.addMesa(); // Añadir una nueva mesa numerada automáticamente
            closeFabMenu();
        });

        fabRemove.setOnClickListener(v -> {
            mesaAdapter.removeMesa(); // Eliminar la última mesa
            closeFabMenu();
        });
    }

    private void openFabMenu() {
        isFabOpen = true;
        fabAdd.setVisibility(View.VISIBLE);
        fabRemove.setVisibility(View.VISIBLE);
        fabMain.setImageResource(R.drawable.close); // Cambia el icono del FAB principal a una X o icono de cerrar
    }

    private void closeFabMenu() {
        isFabOpen = false;
        fabAdd.setVisibility(View.GONE);
        fabRemove.setVisibility(View.GONE);
        fabMain.setImageResource(R.drawable.add); // Cambia el icono del FAB principal a un +
    }

    private List<Mesa> getMesas() {
        List<Mesa> mesas = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            mesas.add(new Mesa("Mesa " + i, "0.00 €"));
        }
        return mesas;
    }
}
