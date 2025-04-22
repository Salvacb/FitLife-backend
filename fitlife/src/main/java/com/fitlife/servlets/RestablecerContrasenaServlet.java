package com.fitlife.servlets;

import com.fitlife.utils.SeguridadUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/restablecer")
public class RestablecerContrasenaServlet extends HttpServlet {

    private final String URL_DB = "jdbc:h2:file:/home/salva/fitlife_db";
    private final String USER_DB = "sa";
    private final String PASS_DB = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String token = request.getParameter("token");

        if (token == null || token.isEmpty()) {
            response.sendRedirect("inicio.jsp");
            return;
        }

        try (Connection conn = DriverManager.getConnection(URL_DB, USER_DB, PASS_DB);
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT usado FROM tokens_recuperacion WHERE token = ?")) {

            stmt.setString(1, token);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                boolean usado = rs.getBoolean("usado");
                if (usado) {
                    request.setAttribute("mensaje", "Este enlace ya fue utilizado.");
                    request.getRequestDispatcher("inicio.jsp").forward(request, response);
                } else {
                    request.setAttribute("token", token);
                    request.getRequestDispatcher("restablecer.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("mensaje", "Token no válido.");
                request.getRequestDispatcher("inicio.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("inicio.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String token = request.getParameter("token");
        String nuevaPassword = request.getParameter("password");

        if (token == null || token.isEmpty() || nuevaPassword == null || nuevaPassword.isEmpty()) {
            response.sendRedirect("inicio.jsp");
            return;
        }

        try (Connection conn = DriverManager.getConnection(URL_DB, USER_DB, PASS_DB)) {

            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT usuario_id, usado FROM tokens_recuperacion WHERE token = ?");
            stmt.setString(1, token);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                boolean yaUsado = rs.getBoolean("usado");
                int usuarioId = rs.getInt("usuario_id");

                if (yaUsado) {
                    request.setAttribute("mensaje", "Este enlace ya fue utilizado.");
                    request.getRequestDispatcher("inicio.jsp").forward(request, response);
                    return;
                }

                // Hashear la nueva contraseña
                String hashedPassword = SeguridadUtil.hashearPassword(nuevaPassword);

                // Actualizar contraseña
                PreparedStatement updatePwd = conn.prepareStatement(
                        "UPDATE usuarios SET password = ? WHERE id = ?");
                updatePwd.setString(1, hashedPassword);
                updatePwd.setInt(2, usuarioId);
                updatePwd.executeUpdate();

                // Marcar token como usado
                PreparedStatement marcar = conn.prepareStatement(
                        "UPDATE tokens_recuperacion SET usado = TRUE WHERE token = ?");
                marcar.setString(1, token);
                marcar.executeUpdate();

                request.setAttribute("mensaje", "✅ Contraseña restablecida correctamente. Iniciá sesión.");
                request.getRequestDispatcher("login.jsp").forward(request, response);

            } else {
                request.setAttribute("mensaje", "Token no válido.");
                request.getRequestDispatcher("inicio.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error al restablecer la contraseña.");
            request.getRequestDispatcher("inicio.jsp").forward(request, response);
        }
    }
}
