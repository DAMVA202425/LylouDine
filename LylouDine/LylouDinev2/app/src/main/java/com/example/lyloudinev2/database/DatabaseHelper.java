package com.example.lyloudinev2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "restaurant.db";
    private static final int DATABASE_VERSION = 2; // Incrementa la versión porque vas a modificar el esquema

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ROLE = "role";

    public static final String TABLE_MESAS = "mesas";
    public static final String COLUMN_MESA_ID = "mesa_id";
    public static final String COLUMN_MESA_NAME = "mesa_name";
    public static final String COLUMN_MESA_CATEGORY = "mesa_category";
    public static final String COLUMN_MESA_ICON = "icon_resource"; // Nuevo campo para el icono

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
                    COLUMN_MESA_ICON + " INTEGER DEFAULT 0);"; // Incluye el nuevo campo en la creación de la tabla

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_USERS);
        db.execSQL(TABLE_CREATE_MESAS);

        // Insertar un usuario predeterminado
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, "lylou");
        values.put(COLUMN_PASSWORD, "lylou123"); // En una aplicación real, asegúrate de hashear esta contraseña
        values.put(COLUMN_ROLE, "admin");
        db.insert(TABLE_USERS, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            // Solo agrega la columna si la base de datos está en una versión anterior a la 2
            db.execSQL("ALTER TABLE " + TABLE_MESAS + " ADD COLUMN " + COLUMN_MESA_ICON + " INTEGER DEFAULT 0");
        }
    }
}
