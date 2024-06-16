package com.example.PC2_FabianUrcia.dto;

public class Proyecto {
    private int id;
    private String codigoProyecto;
    private String codigoActividad;
    private String estado;
    private String observacion;
    private String fecha;

    public Proyecto() {
    }

    public Proyecto(int id, String codigoProyecto, String codigoActividad, String estado, String observacion, String fecha) {
        this.id = id;
        this.codigoProyecto = codigoProyecto;
        this.codigoActividad = codigoActividad;
        this.estado = estado;
        this.observacion = observacion;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoProyecto() {
        return codigoProyecto;
    }

    public void setCodigoProyecto(String codigoProyecto) {
        this.codigoProyecto = codigoProyecto;
    }

    public String getCodigoActividad() {
        return codigoActividad;
    }

    public void setCodigoActividad(String codigoActividad) {
        this.codigoActividad = codigoActividad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
