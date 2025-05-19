package com.example.fitlifeapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "rutinas")
public class RutinaEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String dia;
    private String descripcion;
    private boolean sincronizada;

    public RutinaEntity(String dia, String descripcion) {
        this.dia = dia;
        this.descripcion = descripcion;
        this.sincronizada = false;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDia() { return dia; }
    public String getDescripcion() { return descripcion; }

    public boolean isSincronizada() {
        return sincronizada;
    }

    public void setSincronizada(boolean sincronizada) {
        this.sincronizada = sincronizada;
    }
}
