package com.example.lyloudinev2.admin;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lyloudinev2.DeliveryOrderAdapter;
import com.example.lyloudinev2.R;
import com.example.lyloudinev2.SimulateOrderActivity;

import com.example.lyloudinev2.database.DatabaseHelper;
import com.example.lyloudinev2.DeliveryOrder;

import java.util.ArrayList;
import java.util.List;

public class DeliveryFragment extends Fragment {

    private RecyclerView recyclerView;
    private DeliveryOrderAdapter adapter;
    private List<DeliveryOrder> deliveryOrders;
    private DatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delivery, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        SearchView searchView = view.findViewById(R.id.search_view);
        Button btnSimulateOrder = view.findViewById(R.id.btn_simulate_order);

        dbHelper = new DatabaseHelper(getContext());
        deliveryOrders = getDeliveryOrdersFromDB();
        adapter = new DeliveryOrderAdapter(deliveryOrders);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });

        btnSimulateOrder.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SimulateOrderActivity.class);
            startActivity(intent);
        });

        return view;
    }

    private List<DeliveryOrder> getDeliveryOrdersFromDB() {
        List<DeliveryOrder> orders = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_DELIVERY_ORDERS, null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ORDER_ID));
                String orderNumber = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ORDER_NUMBER));
                String customerName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CUSTOMER_NAME));
                String address = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ADDRESS));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PHONE));
                String deliveryTime = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DELIVERY_TIME));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PRICE));
                String status = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_STATUS));
                orders.add(new DeliveryOrder(id, orderNumber, customerName, address, phone, deliveryTime, price, status));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return orders;
    }

    private void filter(String text) {
        List<DeliveryOrder> filteredList = new ArrayList<>();
        for (DeliveryOrder order : deliveryOrders) {
            if (order.getCustomerName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(order);
            }
        }
        adapter.filterList(filteredList);
    }
}
