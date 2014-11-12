<%@page import="java.util.ArrayList"%>
<%@page import="es.mundo.modelo.Pais"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
      <!--  recuperar el Pais q está en el request -->
      <!--  le doy la vuelta al objeto "pais" para tener los 
      <!--  metodos get y set -->
      <%
      	ArrayList<Pais> paises= (ArrayList<Pais>)request.getAttribute("listado");
      %>

<table>
 <tr>
     <th>Id </th>
     <th>Nombre </th>
     <th>Habitantes </th>
</tr>
<!--  mostrarlo. Aquí comienza el foreach -->
<%
	for(Pais pais:paises){
%>
 <tr>
      <td><%=pais.getId() %> </td>
      <td><%=pais.getNombre() %> </td>
      <td><%=pais.getHabitantes() %> </td>
</tr>
<%}// cerramos el for %>
</table>
</br></br></br>
  <a href="index.html">Ir a inicio</a>
</body>

</html>