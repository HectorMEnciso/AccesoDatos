<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- Visaliza un formulario para realizar la entrada de datos de un departamento. Los datos del formulario
se enviarán a través del JavaBean Departamentos.java al controlador, el nombre del parámetro que contendrá
los datos del departamento a insertar es depart y se usará en el controlador con el método getAttribute() -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ALTA DE DEPARTAMENTOS</title>
</head>
<%
if (request.getParameter("dept_no")!=null){%>
<jsp:forward page="/Controlador?accion=insertar"/>
<%} %>
<body>
<center>
		<h2>ENTRADA DE DATOS DE DEPARTAMENTOS</h2>
		<br />
		<form method="post">
			<p>
				Número de departamento: <input name="dept_no" type="text" size="5">
			</p>
			<p>
				Nombre: <input name="dnombre" type="text" size="15">
			</p>
			<p>
				Localidad: <input name="loc" type="text" size=15>
			</p>
			<input type="submit" name="insertar" value="Insertar departamento." />
			<input type="reset" name="cancelar" value="Cancelar entrada." />
		</form>
	</center>
</body>
</html>