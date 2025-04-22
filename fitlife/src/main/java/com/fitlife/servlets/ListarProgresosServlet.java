package com.fitlife.servlets;

import com.fitlife.classes.Usuario;
import com.fitlife.classes.Progreso;
import com.fitlife.dao.ProgresoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/listarProgresos")
public class ListarProgresosServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuario") : null;

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Progreso> progresos = ProgresoDAO.obtenerProgresosPorUsuario(usuario.getId());
        request.setAttribute("progresos", progresos);
        request.getRequestDispatcher("listar_progresos.jsp").forward(request, response);
    }
}
