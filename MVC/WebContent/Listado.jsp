<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import= "clases.Departamentos, java.util.*"	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LISTADO DE LOS DEPARTAMENTOS</title>
</head>
<body>
	<center>
		<h2>LISTADO DE DEPARTAMENTOS</h2>
		<table border="1">
			<tr>
				<th>Departamento</th>
				<th>Nombre</th>
				<th>Localidad</th>
			</tr>
			<%
				ArrayList listadep=(ArrayList)request.getAttribute("departamentos");
			if(listadep!=null)
				for (int i=0;i<listadep.size();i++){
					Departamentos dep=(Departamentos)listadep.get(i);
			%>
			<tr>
				<td><%=Byte.toString(dep.getDeptno())%></td>
				<td><%=dep.getDnombre()%></td>
				<td><%=dep.getLoc()%></td>
			</tr>
			<%
				}
			%>
		</table>
		<br />
		<br /> <a href="index.html">Inicio</a>
	</center>
</body>
</html>