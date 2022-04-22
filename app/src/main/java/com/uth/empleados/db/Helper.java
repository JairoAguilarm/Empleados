package com.uth.empleados.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Helper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "db_empleados.db";
    public static final String DATA_TABLE = "tb_empleados";


    public Helper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + DATA_TABLE + "(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "tb_nombres TEXT NOT NULL," +
                        "tb_apellidos TEXT NOT NULL," +
                        "tb_edad TEXT NOT NULL," +
                        "tb_direccion TEXT NOT NULL," +
                        "tb_puesto TEXT NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATA_TABLE );
        onCreate(sqLiteDatabase);

    }

}
