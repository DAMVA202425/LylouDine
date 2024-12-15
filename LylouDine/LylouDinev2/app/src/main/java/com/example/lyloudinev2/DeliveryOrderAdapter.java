package com.example.lyloudinev2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DeliveryOrderAdapter extends RecyclerView.Adapter<DeliveryOrderAdapter.ViewHolder> {

    private List<DeliveryOrder> deliveryOrders;

    public DeliveryOrderAdapter(List<DeliveryOrder> deliveryOrders) {
        this.deliveryOrders = deliveryOrders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_delivery_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DeliveryOrder order = deliveryOrders.get(position);
        holder.tvOrderNumber.setText(order.getOrderNumber());
        holder.tvCustomerName.setText(order.getCustomerName());
        holder.tvAddress.setText(order.getAddress());
        holder.tvPhone.setText(order.getPhone());
        holder.tvDeliveryTime.setText(order.getDeliveryTime());
        holder.tvPrice.setText(String.format("$%.2f", order.getPrice()));
        holder.tvStatus.setText(order.getStatus());
    }

    @Override
    public int getItemCount() {
        return deliveryOrders.size();
    }

    public void filterList(List<DeliveryOrder> filteredList) {
        this.deliveryOrders = filteredList;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderNumber, tvCustomerName, tvAddress, tvPhone, tvDeliveryTime, tvPrice, tvStatus;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderNumber = itemView.findViewById(R.id.tvOrderNumber);
            tvCustomerName = itemView.findViewById(R.id.tvCustomerName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvDeliveryTime = itemView.findViewById(R.id.tvDeliveryTime);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }
    }
}
