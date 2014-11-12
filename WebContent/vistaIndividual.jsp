<%@page import="es.mundo.modelo.Pais"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vista individual</title>
</head>
<body>
      <!--  recuperar el Pais q está en el request -->
      <!--  le doy la vuelta al objeto "pais" para tener los 
      <!--  metodos get y set -->
<form action="Procesar" method="get">      
      <%
            	Pais paisDePaisDAO= (Pais)request.getAttribute("pais");
            %>

<table>
 <tr>
     <th>Id </th>
     <th>Nombre </th>
     <th>Habitantes </th>
</tr>
<!--  mostrarlo -->
 <tr>
      <td><input type="text" name="id" value="<%=paisDePaisDAO.getId()%>" readonly="readonly"/></td>
      <td><input type="text" name="nombre" value="<%=paisDePaisDAO.getNombre()%>"/></td>
      <td><input type="text" name="habitantes" value="<%=paisDePaisDAO.getHabitantes()%>"/></td>
</tr>
</table>
<br></br>
<input type="submit" value="Borrar" id="borrar" name="borrar"/>
<input type="submit" value="Actualizar" id="actualizar" name="actualizar"/>
<br></br>
<a href="index.html">Ir a inicio</a>
</form> 
</body>
</html>