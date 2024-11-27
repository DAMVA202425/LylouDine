package com.example.lyloudinev2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lyloudinev2.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class MesaDAO {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public MesaDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addMesa(String name, String category) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_MESA_NAME, name);
        values.put(DatabaseHelper.COLUMN_MESA_CATEGORY, category);

        database.insert(DatabaseHelper.TABLE_MESAS, null, values);
    }

    public void deleteMesa(long id) {
        database.delete(DatabaseHelper.TABLE_MESAS, DatabaseHelper.COLUMN_MESA_ID + " = " + id, null);
    }

    public List<Mesa> getMesas(String category) {
        List<Mesa> mesas = new ArrayList<>();

        Cursor cursor = database.query(DatabaseHelper.TABLE_MESAS,
                new String[]{DatabaseHelper.COLUMN_MESA_ID, DatabaseHelper.COLUMN_MESA_NAME, DatabaseHelper.COLUMN_MESA_CATEGORY},
                DatabaseHelper.COLUMN_MESA_CATEGORY + " = ?", new String[]{category}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Mesa mesa = new Mesa(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
                mesas.add(mesa);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return mesas;
    }
}
