package com.fitlife.dao;

import com.fitlife.bd.ConexionBD;
import com.fitlife.classes.Progreso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgresoDAO {

    public static boolean guardarProgreso(Progreso progreso) {
        String sql = "INSERT INTO progresos (usuario_id, fecha, peso, calorias, observaciones) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, progreso.getUsuarioId());
            stmt.setDate(2, progreso.getFecha());
            stmt.setDouble(3, progreso.getPeso());
            stmt.setInt(4, progreso.getCalorias());
            stmt.setString(5, progreso.getObservaciones());

            int filas = stmt.executeUpdate();
            System.out.println("[PROGRESO] Registro guardado. Filas afectadas: " + filas);
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("[ERROR] Error al guardar progreso:");
            e.printStackTrace();
            return false;
        }
    }

    public static List<Progreso> obtenerProgresosPorUsuario(int usuarioId) {
        List<Progreso> lista = new ArrayList<>();
        String sql = "SELECT * FROM progresos WHERE usuario_id = ? ORDER BY fecha DESC";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Progreso p = new Progreso();
                p.setId(rs.getInt("id"));
                p.setUsuarioId(rs.getInt("usuario_id"));
                p.setFecha(rs.getDate("fecha"));
                p.setPeso(rs.getDouble("peso"));
                p.setCalorias(rs.getInt("calorias"));
                p.setObservaciones(rs.getString("observaciones"));
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
