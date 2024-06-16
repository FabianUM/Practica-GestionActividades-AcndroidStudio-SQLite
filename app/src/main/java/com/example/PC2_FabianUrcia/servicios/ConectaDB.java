package com.example.PC2_FabianUrcia.servicios;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.PC2_FabianUrcia.interfaces.ConstantesApp;

public class ConectaDB extends SQLiteOpenHelper {

    public ConectaDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ConstantesApp.DDL_PROYECTO);
        db.execSQL(ConstantesApp.DDL_ESTADO);

        // Insert initial data into estado table
        db.execSQL("INSERT INTO " + ConstantesApp.TBL_ESTADO + " (nombre) VALUES ('Pendiente');");
        db.execSQL("INSERT INTO " + ConstantesApp.TBL_ESTADO + " (nombre) VALUES ('En Progreso');");
        db.execSQL("INSERT INTO " + ConstantesApp.TBL_ESTADO + " (nombre) VALUES ('Completado');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesApp.TBL_PROYECTO);
        onCreate(db);
    }
}
