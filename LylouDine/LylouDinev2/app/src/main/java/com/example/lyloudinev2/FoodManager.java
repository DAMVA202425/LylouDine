package com.example.lyloudinev2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lyloudinev2.database.DatabaseHelper;

public class FoodManager {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public FoodManager(Context context) {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public long addFood(String name, double price) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_FOOD_NAME, name);
        values.put(DatabaseHelper.COLUMN_FOOD_PRICE, price);
        return database.insert(DatabaseHelper.TABLE_FOOD, null, values);
    }

    public Cursor getAllFood() {
        return database.query(DatabaseHelper.TABLE_FOOD, null, null, null, null, null, null);
    }

    public void close() {
        dbHelper.close();
    }
}
