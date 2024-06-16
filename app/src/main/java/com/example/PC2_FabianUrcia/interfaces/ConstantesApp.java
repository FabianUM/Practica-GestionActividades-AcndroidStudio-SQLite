package com.example.PC2_FabianUrcia.interfaces;

public interface ConstantesApp {
    String DDL_PROYECTO = "CREATE TABLE proyecto (\n" +
            "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    codigo_proyecto TEXT NOT NULL,\n" +
            "    codigo_actividad TEXT NOT NULL,\n" +
            "    estado TEXT NOT NULL,\n" +
            "    observacion TEXT,\n" +
            "    fecha TEXT NOT NULL\n" +
            ");";
    String DDL_ESTADO = "CREATE TABLE estado (\n" +
            "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    nombre TEXT NOT NULL\n" +
            ");";

    String TBL_PROYECTO = "proyecto";
    String TBL_ESTADO = "estado";
    String BDD = "proyectos.db";
    int VERSION = 1;
}
