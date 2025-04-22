<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.fitlife.classes.Usuario" %>
<html>
<head>
    <title>Registro de Usuario</title>
</head>
<body>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario != null) {
%>
    <p>Ya has iniciado sesión como <strong><%= usuario.getNombre() %></strong>.</p>
    <a href="main.jsp">Ir al panel principal</a>
    <form action="logout" method="post">
        <button type="submit">Cerrar sesión</button>
    </form>
<%
    } else {
%>
    <h2>Registro en FitLife</h2>

    <form method="post" action="registro">
        Nombre: <input type="text" name="nombre" required /><br/><br/>
        Email: <input type="email" name="email" required /><br/><br/>
        Contraseña: <input type="password" name="password" required /><br/><br/>
        
        Sexo:
        <select name="sexo" required>
            <option value="">--Selecciona--</option>
            <option value="Hombre">Hombre</option>
            <option value="Mujer">Mujer</option>
        </select><br/><br/>
    
        <input type="submit" value="Registrarse" />
    </form>
    

    <p>¿Ya tienes cuenta? <a href="login.jsp">Inicia sesión</a></p>
    <p><a href="inicio.jsp">← Volver al inicio</a></p>
<%
    }
%>

<p style="color: green;">
    <%= request.getAttribute("mensaje") != null ? request.getAttribute("mensaje") : "" %>
</p>

</body>
</html>
