package com.example.lyloudinev2;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lyloudinev2.database.DatabaseHelper;

public class SimulateOrderActivity extends AppCompatActivity {

    private EditText etCustomerName, etAddress, etPhone, etDeliveryTime, etPrice;
    private Button btnAddOrder;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulate_order);

        etCustomerName = findViewById(R.id.etCustomerName);
        etAddress = findViewById(R.id.etAddress);
        etPhone = findViewById(R.id.etPhone);
        etDeliveryTime = findViewById(R.id.etDeliveryTime);
        etPrice = findViewById(R.id.etPrice);
        btnAddOrder = findViewById(R.id.btnAddOrder);
        dbHelper = new DatabaseHelper(this);

        btnAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customerName = etCustomerName.getText().toString();
                String address = etAddress.getText().toString();
                String phone = etPhone.getText().toString();
                String deliveryTime = etDeliveryTime.getText().toString();
                double price = Double.parseDouble(etPrice.getText().toString());

                ContentValues values = new ContentValues();
                values.put(DatabaseHelper.COLUMN_CUSTOMER_NAME, customerName);
                values.put(DatabaseHelper.COLUMN_ADDRESS, address);
                values.put(DatabaseHelper.COLUMN_PHONE, phone);
                values.put(DatabaseHelper.COLUMN_DELIVERY_TIME, deliveryTime);
                values.put(DatabaseHelper.COLUMN_PRICE, price);
                values.put(DatabaseHelper.COLUMN_STATUS, "En proceso");

                dbHelper.getWritableDatabase().insert(DatabaseHelper.TABLE_DELIVERY_ORDERS, null, values);

                // Limpiar campos y regresar a la pantalla de Delivery
                etCustomerName.setText("");
                etAddress.setText("");
                etPhone.setText("");
                etDeliveryTime.setText("");
                etPrice.setText("");

                finish();
            }
        });
    }
}
