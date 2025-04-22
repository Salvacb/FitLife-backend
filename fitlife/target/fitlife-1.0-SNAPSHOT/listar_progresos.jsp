<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.fitlife.classes.Progreso" %>
<%@ page import="com.fitlife.classes.Usuario" %>
<%@ page import="com.fitlife.utils.NutricionUtil" %>

<html>
<head>
    <title>Historial de Progreso</title>
</head>
<body>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Progreso> progresos = (List<Progreso>) request.getAttribute("progresos");
    int caloriasRecomendadas = NutricionUtil.getCaloriasRecomendadas(usuario);
%>

<h2>📊 Historial de Progreso de <%= usuario.getNombre() %></h2>

<% if (usuario.getNivelActividad() != null) { %>
    <h3>🔥 Calorías recomendadas por día:
        <%= NutricionUtil.getCaloriasRecomendadas(usuario) %> kcal
    </h3>
<% } else { %>
    <p style="color:orange;">⚠️ Aún no has configurado tu nivel de actividad.</p>
<% } %>

<%
    if (progresos == null || progresos.isEmpty()) {
%>
    <p style="color: darkred; font-weight: bold;">
        🛑 Aún no has registrado ningún progreso.
    </p>
    <p>
        Puedes hacerlo desde el panel principal usando la opción
        <strong>“📈 Registrar Progreso”</strong>.
    </p>
<%
    } else {
%>
    <h3>🔥 Calorías recomendadas por día: <%= NutricionUtil.getCaloriasRecomendadas(usuario) %> kcal</h3>

    <table border="1" cellpadding="8">
        <tr>
            <th>Fecha</th>
            <th>Peso (kg)</th>
            <th>Calorías</th>
            <th>Observaciones</th>
        </tr>
        <% for (Progreso p : progresos) { %>
        <tr>
            <td><%= p.getFecha() %></td>
            <td><%= p.getPeso() %></td>
            <td><%= p.getCalorias() %></td>
            <td><%= p.getObservaciones() %></td>
        </tr>
        <% } %>
    </table>
<%
    }
%>


<br/>
<p><a href="main.jsp">← Volver al panel principal</a></p>

</body>
</html>
