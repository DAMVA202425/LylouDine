package com.example.lyloudine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class CategoriaFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categoria, container, false);

        Button btnPrimeraPlanta = view.findViewById(R.id.b_PPlanta);
        Button btnSegundaPlanta = view.findViewById(R.id.b_SPlanta);
        Button btnTerraza = view.findViewById(R.id.b_Terraza);

        btnPrimeraPlanta.setOnClickListener(v -> loadFragment(new PrimeraPlantaFragment()));
        btnSegundaPlanta.setOnClickListener(v -> loadFragment(new SegundaPlantaFragment()));
        btnTerraza.setOnClickListener(v -> loadFragment(new TerrazaFragment()));

        return view;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.child_fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
