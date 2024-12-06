package com.example.lyloudinev2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class MesaAdapter extends RecyclerView.Adapter<MesaAdapter.MesaViewHolder> {
    private Context context;
    private List<Mesa> mesaList;

    public MesaAdapter(Context context, List<Mesa> mesaList) {
        this.context = context;
        this.mesaList = mesaList;
    }

    @NonNull
    @Override
    public MesaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mesa_item, parent, false);
        return new MesaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MesaViewHolder holder, int position) {
        Mesa mesa = mesaList.get(position);
        holder.mesaButton.setText(mesa.getName());

        // Verifica que el recurso del icono sea válido
        if (mesa.getIconResource() != 0) {
            holder.mesaButton.setIcon(ContextCompat.getDrawable(context, mesa.getIconResource())); // Configurar el icono del botón
        }

        holder.mesaButton.setOnClickListener(v -> {
            // Acción al hacer clic en el botón de la mesa
        });
    }

    @Override
    public int getItemCount() {
        return mesaList.size();
    }

    public static class MesaViewHolder extends RecyclerView.ViewHolder {
        MaterialButton mesaButton;

        public MesaViewHolder(@NonNull View itemView) {
            super(itemView);
            mesaButton = itemView.findViewById(R.id.mesa_button);
        }
    }
}
