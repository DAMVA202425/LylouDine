package com.example.lyloudinev2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "restaurant.db";
    private static final int DATABASE_VERSION = 4;

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ROLE = "role";

    public static final String TABLE_MESAS = "mesas";
    public static final String COLUMN_MESA_ID = "mesa_id";
    public static final String COLUMN_MESA_NAME = "mesa_name";
    public static final String COLUMN_MESA_CATEGORY = "mesa_category";
    public static final String COLUMN_MESA_ICON = "icon_resource";

    public static final String TABLE_DELIVERY_ORDERS = "delivery_orders";
    public static final String COLUMN_ORDER_ID = "order_id";
    public static final String COLUMN_ORDER_NUMBER = "order_number";
    public static final String COLUMN_CUSTOMER_NAME = "customer_name";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_DELIVERY_TIME = "delivery_time";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_STATUS = "status";

    public static final String TABLE_FOOD = "food";
    public static final String COLUMN_FOOD_ID = "food_id";
    public static final String COLUMN_FOOD_NAME = "food_name";
    public static final String COLUMN_FOOD_PRICE = "food_price";

    private static final String TABLE_CREATE_USERS =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USERNAME + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT, " +
                    COLUMN_ROLE + " TEXT);";

    private static final String TABLE_CREATE_MESAS =
            "CREATE TABLE " + TABLE_MESAS + " (" +
                    COLUMN_MESA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_MESA_NAME + " TEXT, " +
                    COLUMN_MESA_CATEGORY + " TEXT, " +
                    COLUMN_MESA_ICON + " INTEGER DEFAULT 0);";

    private static final String TABLE_CREATE_DELIVERY_ORDERS =
            "CREATE TABLE " + TABLE_DELIVERY_ORDERS + " (" +
                    COLUMN_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_ORDER_NUMBER + " TEXT, " +
                    COLUMN_CUSTOMER_NAME + " TEXT, " +
                    COLUMN_ADDRESS + " TEXT, " +
                    COLUMN_PHONE + " TEXT, " +
                    COLUMN_DELIVERY_TIME + " TEXT, " +
                    COLUMN_PRICE + " REAL, " +
                    COLUMN_STATUS + " TEXT);";

    private static final String TABLE_CREATE_FOOD =
            "CREATE TABLE " + TABLE_FOOD + " (" +
                    COLUMN_FOOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_FOOD_NAME + " TEXT, " +
                    COLUMN_FOOD_PRICE + " REAL);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_USERS);
        db.execSQL(TABLE_CREATE_MESAS);
        db.execSQL(TABLE_CREATE_DELIVERY_ORDERS);
        db.execSQL(TABLE_CREATE_FOOD);

        // Ejecutar la consulta para eliminar todas las mesas
        db.execSQL("DELETE FROM " + TABLE_MESAS);

        // Insertar un usuario predeterminado
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, "lylou");
        values.put(COLUMN_PASSWORD, "lylou123");
        values.put(COLUMN_ROLE, "admin");
        db.insert(TABLE_USERS, null, values);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE " + TABLE_MESAS + " ADD COLUMN " + COLUMN_MESA_ICON + " INTEGER DEFAULT 0");
        }
        if (oldVersion < 3) {
            db.execSQL(TABLE_CREATE_DELIVERY_ORDERS);
        }
        if (oldVersion < 4) {
            db.execSQL(TABLE_CREATE_FOOD);
        }if(oldVersion<5){
            db.execSQL("INSERT INTO food (food_name, food_price) VALUES ('Ceviche', 18.50);");
            db.execSQL("INSERT INTO food (food_name, food_price) VALUES ('Lomo Saltado', 20.00);");
            db.execSQL("INSERT INTO food (food_name, food_price) VALUES ('Ají de Gallina', 15.00);");
            db.execSQL("INSERT INTO food (food_name, food_price) VALUES ('Pisco Sour', 12.00);");
            db.execSQL("INSERT INTO food (food_name, food_price) VALUES ('Chicha Morada', 5.00);");
            db.execSQL("INSERT INTO food (food_name, food_price) VALUES ('Anticuchos', 10.00);");
            db.execSQL("INSERT INTO food (food_name, food_price) VALUES ('Papa a la Huancaína', 8.00);");
            db.execSQL("INSERT INTO food (food_name, food_price) VALUES ('Arroz con Pollo', 16.00);");
            db.execSQL("INSERT INTO food (food_name, food_price) VALUES ('Causa Limeña', 14.00);");
            db.execSQL("INSERT INTO food (food_name, food_price) VALUES ('Suspiro a la Limeña', 7.50);");

            db.execSQL("INSERT INTO delivery_orders (order_number, customer_name, address, phone, delivery_time, price, status) VALUES ('1001', 'Juan Pérez', 'Calle Falsa 123', '123-456-7890', '18:30', 35.00, 'En proceso');");
            db.execSQL("INSERT INTO delivery_orders (order_number, customer_name, address, phone, delivery_time, price, status) VALUES ('1002', 'María García', 'Avenida Siempre Viva 742', '987-654-3210', '19:00', 50.00, 'En camino');");
            db.execSQL("INSERT INTO delivery_orders (order_number, customer_name, address, phone, delivery_time, price, status) VALUES ('1003', 'Carlos Sánchez', 'Boulevard de los Sueños Rotos 456', '555-555-5555', '20:00', 25.00, 'Entregado');");


        }
    }
}
