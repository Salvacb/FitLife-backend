package com.fitlife.utils;

import com.fitlife.classes.Usuario;

public class NutricionUtil {

    // Fórmula de Mifflin-St Jeor
    public static double calcularTMB(Usuario usuario) {
        double tmb = (10 * usuario.getPeso()) +
                     (6.25 * usuario.getAltura()) -
                     (5 * usuario.getEdad());

        if (usuario.getSexo().equalsIgnoreCase("Mujer")) {
            tmb -= 161;
        } else {
            tmb += 5;
        }

        return tmb;
    }

    public static double getFactorActividad(String nivel) {
        if (nivel == null) return 1.2; // Valor por defecto si no se ha configurado
    
        return switch (nivel) {
            case "Sedentario" -> 1.2;
            case "Ligeramente activo" -> 1.375;
            case "Moderadamente activo" -> 1.55;
            case "Muy activo" -> 1.725;
            case "Extra activo" -> 1.9;
            default -> 1.2;
        };
    }
    

    public static int getCaloriasRecomendadas(Usuario usuario) {
        double tmb = calcularTMB(usuario);
        double factor = getFactorActividad(usuario.getNivelActividad());
        return (int) (tmb * factor);
    }
}
