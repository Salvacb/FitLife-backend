package com.fitlife.servlets;

import com.fitlife.classes.Usuario;
import com.fitlife.dao.UsuarioDAO;
import com.fitlife.utils.SeguridadUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Usuario usuario = UsuarioDAO.buscarPorEmail(email);

        if (usuario != null && usuario.getPassword().equals(SeguridadUtil.hashearPassword(password))) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            response.sendRedirect("main.jsp");
        } else {
            request.setAttribute("mensaje", "Correo o contraseña incorrectos.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }
}
