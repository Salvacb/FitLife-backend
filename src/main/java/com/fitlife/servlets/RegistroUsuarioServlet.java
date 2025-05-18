package com.fitlife.servlets;

import com.fitlife.classes.Usuario;
import com.fitlife.dao.UsuarioDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/registro")
public class RegistroUsuarioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String sexo = request.getParameter("sexo"); // 🆕 Captura del nuevo campo

        Usuario nuevoUsuario = new Usuario(0, nombre, email, password);
        nuevoUsuario.setSexo(sexo); // 🆕 Asignamos el valor al usuario

        boolean exito = UsuarioDAO.registrarUsuario(nuevoUsuario);

        if (exito) {
            request.setAttribute("mensaje", "¡Registro exitoso!");
        } else {
            request.setAttribute("mensaje", "Error: El correo ya está registrado.");
        }

        request.getRequestDispatcher("registro.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("registro.jsp");
    }
}
