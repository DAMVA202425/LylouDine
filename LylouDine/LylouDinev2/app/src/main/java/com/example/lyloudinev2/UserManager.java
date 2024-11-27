package com.example.lyloudinev2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lyloudinev2.database.DatabaseHelper;

public class UserManager {
    private static UserManager instance;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    // Constructor privado para evitar instanciación directa
    private UserManager(Context context) {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    // Método para obtener la instancia única de UserManager
    public static synchronized UserManager getInstance(Context context) {
        if (instance == null) {
            instance = new UserManager(context.getApplicationContext());
        }
        return instance;
    }

    // Métodos para agregar, autenticar y obtener roles de usuarios
    public void addUser(String username, String password, String role) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USERNAME, username);
        values.put(DatabaseHelper.COLUMN_PASSWORD, password);
        values.put(DatabaseHelper.COLUMN_ROLE, role);
        database.insert(DatabaseHelper.TABLE_USERS, null, values);
    }

    public boolean authenticate(String username, String password) {
        String[] columns = { DatabaseHelper.COLUMN_USER_ID };
        String selection = DatabaseHelper.COLUMN_USERNAME + " = ? AND " +
                DatabaseHelper.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = { username, password };
        Cursor cursor = database.query(DatabaseHelper.TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    public String getRole(String username) {
        String[] columns = { DatabaseHelper.COLUMN_ROLE };
        String selection = DatabaseHelper.COLUMN_USERNAME + " = ?";
        String[] selectionArgs = { username };
        Cursor cursor = null;

        try {
            cursor = database.query(DatabaseHelper.TABLE_USERS, columns, selection, selectionArgs, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                int roleIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_ROLE);
                if (roleIndex >= 0) {
                    String role = cursor.getString(roleIndex);
                    return role;
                } else {
                    Log.e("DatabaseHelper", "La columna " + DatabaseHelper.COLUMN_ROLE + " no existe.");
                }
            }
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error al obtener el rol del usuario", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }
}
