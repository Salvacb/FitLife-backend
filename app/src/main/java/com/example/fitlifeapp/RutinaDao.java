package com.example.fitlifeapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RutinaDao {

    @Insert
    void insertarRutinas(List<RutinaEntity> rutinas);

    @Query("SELECT * FROM rutinas")
    List<RutinaEntity> obtenerTodas();

    @Query("SELECT * FROM rutinas WHERE sincronizada = 0")
    List<RutinaEntity> obtenerNoSincronizadas();

    @Query("UPDATE rutinas SET sincronizada = 1 WHERE id = :id")
    void marcarComoSincronizada(int id);
}
