package programas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;


import clases.Departamentos;

public class OperacionesBD {

	//ESTABLECER LA CONEXION CON LA BD
	public Connection getConnection(){
		Connection conexion=null;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conexion=DriverManager.getConnection("jdbc:mysql://localhost/acceso","root","root");
		}catch (Exception e) {e.printStackTrace();}
		return conexion;
	}
	
	//LISTAR - devuelve un array con los departamentos
	public ArrayList listarDep(){
		ArrayList departamentos=new ArrayList();
		try{
			Connection conexion=getConnection();
			Statement sentencia=conexion.createStatement();
			String sql="SELECT * FROM departamentos";
			ResultSet resul=sentencia.executeQuery(sql);
			while (resul.next()){
				Departamentos d=new Departamentos
				(resul.getByte("dept_no"), resul.getString("dnombre"), resul.getString("loc"));
				departamentos.add(d); //añadir departamento al array
			}
		conexion.close();
		}catch (Exception e) {e.printStackTrace();}
		return departamentos;
	}
	
	//INSERTAR - Recibe los datos del departamento  a insertar en la tabla
	public void insertaDepartamento(Departamentos d){
		try{
			Connection conexion=getConnection();
			String sql="INSERT INTO departamentos VALUES (?,?,?)";
			PreparedStatement sent=conexion.prepareStatement(sql);
			sent.setByte(1, d.getDeptno());
			sent.setString(2, d.getDnombre());
			sent.setString(3, d.getLoc());
			if (d.getDeptno()!=0) sent.executeUpdate();
			conexion.close();
		}catch (Exception e) {e.printStackTrace();}
	}
}

