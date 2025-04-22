<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.fitlife.classes.Usuario" %>
<html>
<head>
    <title>FitLife - Inicio</title>
</head>
<body>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario != null) {
%>
    <h2>👋 ¡Hola, <%= usuario.getNombre() %>!</h2>
    <p>Bienvenido a tu espacio FitLife.</p>

    <ul>
        <li><a href="perfil">Ver Perfil</a></li>
        <li><a href="agregar_progreso.jsp">📈 Registrar Progreso</a></li>
        <li><a href="listarProgresos">📃 Ver Historial de Progreso</a></li>
        <li><a href="verRutinaActual">🏋️ Ver Rutina Asignada</a></li> <!-- 🆕 -->
        <li><a href="verRutinas">📋 Ver y Seleccionar Rutina</a></li> <!-- 🆕 -->
        <li><a href="objetivosDia">🎯 Objetivos del Día</a></li>
    </ul>

    <form action="logout" method="post">
        <button type="submit">Cerrar sesión</button>
    </form>
<%
    } else {
%>
    <p>No has iniciado sesión. <a href="login.jsp">Hazlo aquí</a></p>
<%
    }
%>

</body>
</html>
