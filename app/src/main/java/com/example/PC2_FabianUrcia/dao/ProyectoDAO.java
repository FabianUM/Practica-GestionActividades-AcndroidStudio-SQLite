package com.example.PC2_FabianUrcia.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.PC2_FabianUrcia.dto.Proyecto;
import com.example.PC2_FabianUrcia.interfaces.ConstantesApp;
import com.example.PC2_FabianUrcia.servicios.ConectaDB;

import java.util.ArrayList;
import java.util.List;

public class ProyectoDAO {
    private SQLiteDatabase db;

    public ProyectoDAO(Context context) {
        this.db = new ConectaDB(context, ConstantesApp.BDD, null, ConstantesApp.VERSION).getWritableDatabase();
    }

    public String insert(Proyecto project) {
        String resp = "";
        ContentValues registro = new ContentValues();
        registro.put("codigo_proyecto", project.getCodigoProyecto());
        registro.put("codigo_actividad", project.getCodigoActividad());
        registro.put("estado", project.getEstado());
        registro.put("observacion", project.getObservacion());
        registro.put("fecha", project.getFecha());

        long reg = db.insert(ConstantesApp.TBL_PROYECTO, null, registro);
        Log.i("INFOX", "Inserted row ID: " + reg);
        if (reg == -1) {
            resp = "Error inserting data";
        } else {
            resp = "Data inserted successfully";
        }
        return resp;
    }

    public String update(Proyecto p) {
        String resp = "";
        ContentValues registro = new ContentValues();
        registro.put("codigo_proyecto", p.getCodigoProyecto());
        registro.put("codigo_actividad", p.getCodigoActividad());
        registro.put("estado", p.getEstado());
        registro.put("observacion", p.getObservacion());
        registro.put("fecha", p.getFecha());
        int reg = db.update(ConstantesApp.TBL_PROYECTO, registro, "id = ?", new String[]{String.valueOf(p.getId())});
        Log.i("INFOX", "" + reg);
        return resp;
    }

    public void delete(Proyecto p) {
        db.delete(ConstantesApp.TBL_PROYECTO, "id = ?", new String[]{String.valueOf(p.getId())});
    }

    public List<Proyecto> getList() {
        List<Proyecto> lista = new ArrayList<>();
        String cadSQL = "SELECT id, codigo_proyecto, codigo_actividad, estado, observacion, fecha FROM " + ConstantesApp.TBL_PROYECTO + " ORDER BY id DESC";
        Cursor c = db.rawQuery(cadSQL, null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    Proyecto p = new Proyecto();
                    p.setId(c.getInt(c.getColumnIndexOrThrow("id")));
                    p.setCodigoProyecto(c.getString(c.getColumnIndexOrThrow("codigo_proyecto")));
                    p.setCodigoActividad(c.getString(c.getColumnIndexOrThrow("codigo_actividad")));
                    p.setEstado(c.getString(c.getColumnIndexOrThrow("estado")));
                    p.setObservacion(c.getString(c.getColumnIndexOrThrow("observacion")));
                    p.setFecha(c.getString(c.getColumnIndexOrThrow("fecha")));
                    lista.add(p);
                } while (c.moveToNext());
            }
            c.close();
        }
        Log.i("INFOX", "Project list size: " + lista.size());
        return lista;
    }

    public Proyecto getProyectoById(int id) {
        Proyecto proyecto = null;
        String query = "SELECT id, codigo_proyecto, codigo_actividad, estado, observacion, fecha FROM " + ConstantesApp.TBL_PROYECTO + " WHERE id = ?";
        Cursor c = db.rawQuery(query, new String[]{String.valueOf(id)});
        if (c != null && c.moveToFirst()) {
            proyecto = new Proyecto();
            proyecto.setId(c.getInt(c.getColumnIndexOrThrow("id")));
            proyecto.setCodigoProyecto(c.getString(c.getColumnIndexOrThrow("codigo_proyecto")));
            proyecto.setCodigoActividad(c.getString(c.getColumnIndexOrThrow("codigo_actividad")));
            proyecto.setEstado(c.getString(c.getColumnIndexOrThrow("estado")));
            proyecto.setObservacion(c.getString(c.getColumnIndexOrThrow("observacion")));
            proyecto.setFecha(c.getString(c.getColumnIndexOrThrow("fecha")));
            c.close();
        }
        return proyecto;
    }

    public List<Proyecto> getProyectosByFecha(String fecha) {
        List<Proyecto> proyectos = new ArrayList<>();
        String query = "SELECT id, codigo_proyecto, codigo_actividad, estado, observacion, fecha FROM " + ConstantesApp.TBL_PROYECTO + " WHERE fecha = ?";
        Cursor c = db.rawQuery(query, new String[]{fecha});
        if (c != null && c.moveToFirst()) {
            do {
                Proyecto proyecto = new Proyecto();
                proyecto.setId(c.getInt(c.getColumnIndexOrThrow("id")));
                proyecto.setCodigoProyecto(c.getString(c.getColumnIndexOrThrow("codigo_proyecto")));
                proyecto.setCodigoActividad(c.getString(c.getColumnIndexOrThrow("codigo_actividad")));
                proyecto.setEstado(c.getString(c.getColumnIndexOrThrow("estado")));
                proyecto.setObservacion(c.getString(c.getColumnIndexOrThrow("observacion")));
                proyecto.setFecha(c.getString(c.getColumnIndexOrThrow("fecha")));
                proyectos.add(proyecto);
            } while (c.moveToNext());
            c.close();
        }
        return proyectos;
    }

    public List<String> getEstados() {
        List<String> estados = new ArrayList<>();
        String query = "SELECT nombre FROM " + ConstantesApp.TBL_ESTADO;
        Cursor c = db.rawQuery(query, null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    estados.add(c.getString(c.getColumnIndexOrThrow("nombre")));
                } while (c.moveToNext());
            }
            c.close();
        }
        return estados;
    }
}
