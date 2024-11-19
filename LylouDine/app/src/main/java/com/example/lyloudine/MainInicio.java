package com.example.lyloudine;

import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class MainInicio extends AppCompatActivity {

    private RecyclerView r_view;
    private MesaAdapter mesaAdapter;
    private SearchView s_view;
    private FloatingActionButton fabMain, fabAdd, fabRemove;
    private boolean isFabOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_inicio);

        r_view = findViewById(R.id.r_view);
        s_view = findViewById(R.id.s_view);
        fabMain = findViewById(R.id.fab_main);
        fabAdd = findViewById(R.id.fab_add);
        fabRemove = findViewById(R.id.fab_remove);

        r_view.setLayoutManager(new GridLayoutManager(this, 2));

        mesaAdapter = new MesaAdapter(getMesas());
        r_view.setAdapter(mesaAdapter);

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

        fabMain.setOnClickListener(v -> {
            if (isFabOpen) {
                closeFabMenu();
            } else {
                openFabMenu();
            }
        });

        fabAdd.setOnClickListener(v -> {
            // Lógica para añadir una nueva mesa
            mesaAdapter.addMesa(new Mesa("Nueva Mesa", "0.00 €"));
            closeFabMenu();
        });

        fabRemove.setOnClickListener(v -> {
            // Lógica para eliminar la última mesa
            mesaAdapter.removeMesa();
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
        mesas.add(new Mesa("Mesa 1", "0.00 €"));
        mesas.add(new Mesa("Mesa 2", "19.99 €"));
        mesas.add(new Mesa("Mesa 3", "58.60 €"));
        mesas.add(new Mesa("Mesa 4", "16.00 €"));
        mesas.add(new Mesa("Mesa 5", "0.00 €"));
        mesas.add(new Mesa("Mesa 6", "0.00 €"));
        mesas.add(new Mesa("Mesa 7", "0.00 €"));
        mesas.add(new Mesa("Mesa 8", "0.00 €"));
        mesas.add(new Mesa("Mesa 9", "0.00 €"));
        mesas.add(new Mesa("Mesa 10", "0.00 €"));
        return mesas;
    }
}
