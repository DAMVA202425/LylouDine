package com.example.lyloudine;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MesaAdapter extends RecyclerView.Adapter<MesaAdapter.MesaViewHolder> implements Filterable {

    private List<Mesa> mesas;
    private List<Mesa> mesasFull;

    public MesaAdapter(List<Mesa> mesas) {
        this.mesas = mesas;
        this.mesasFull = new ArrayList<>(mesas);
    }

    @NonNull
    @Override
    public MesaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mesa, parent, false);
        return new MesaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MesaViewHolder holder, int position) {
        Mesa mesa = mesas.get(position);
        holder.btnMesa.setText(mesa.getName() + " - " + mesa.getPrice());

        holder.btnMesa.setOnClickListener(v -> {
            // Lógica para gestionar la mesa
            // Por ejemplo, mostrar un diálogo con opciones de gestión
        });
    }

    @Override
    public int getItemCount() {
        return mesas.size();
    }

    @Override
    public Filter getFilter() {
        return mesaFilter;
    }

    private Filter mesaFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Mesa> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mesasFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Mesa item : mesasFull) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mesas.clear();
            mesas.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    static class MesaViewHolder extends RecyclerView.ViewHolder {
        Button btnMesa;

        MesaViewHolder(@NonNull View itemView) {
            super(itemView);
            btnMesa = itemView.findViewById(R.id.b_Mesa);
        }
    }

    public void addMesa(Mesa mesa) {
        mesas.add(mesa);
        mesasFull.add(mesa);
        notifyItemInserted(mesas.size() - 1);
    }

    public void removeMesa() {
        if (!mesas.isEmpty()) {
            int position = mesas.size() - 1;
            mesas.remove(position);
            mesasFull.remove(position);
            notifyItemRemoved(position);
        }
    }
}
