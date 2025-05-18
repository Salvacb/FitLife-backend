<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.fitlife.classes.Usuario" %>
<html>
<head>
    <title>Iniciar Sesión</title>
</head>
<body>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario != null) {
%>
    <p>👋 Bienvenido, <strong><%= usuario.getNombre() %></strong></p>
    <form action="logout" method="post">
        <button type="submit">Cerrar sesión</button>
    </form>
    <p><a href="main.jsp">Ir al panel principal</a></p>
<%
    } else {
%>
    <h2>Iniciar sesión en FitLife</h2>

    <form method="post" action="login">
        Email: <input type="email" name="email" required /><br/><br/>
        Contraseña: <input type="password" name="password" required /><br/><br/>
        <input type="submit" value="Entrar" />
    </form>

    <p>¿No tienes cuenta? <a href="registro.jsp">Regístrate aquí</a></p>
    <p><a href="recuperar.jsp">¿Olvidaste tu contraseña?</a></p>
    <p><a href="inicio.jsp">← Volver al inicio</a></p>
<%
    }
%>

<p style="color: green;">
    <%= request.getAttribute("mensaje") != null ? request.getAttribute("mensaje") : "" %>
</p>

</body>
</html>
