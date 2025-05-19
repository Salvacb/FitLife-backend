package com.example.fitlifeapp;

public class Rutina {
    private String dia;
    private String descripcion;

    // Constructor
    public Rutina(String dia, String descripcion) {
        this.dia = dia;
        this.descripcion = descripcion;
    }

    // Getters
    public String getDia() {
        return dia;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
