package tema2Ejer3;

import java.sql.*;

import com.mysql.jdbc.DatabaseMetaData;

public class ejer3 {
	public static void main(String[] args) {
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();// Cargar el driver, se le pasa este driver JDBC para mySQL
			
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empresa", "root", "root");// establecemos conexion con la bd
			
			DatabaseMetaData dbmd = (DatabaseMetaData) conexion.getMetaData();
			
			String nombre = dbmd.getDatabaseProductName();
			String driver = dbmd.getDriverName();
			String URL = dbmd.getURL();
			String nombreUsu = dbmd.getUserName();
			
			ResultSet tablas = dbmd.getTables(null, "empresa", null, null);//todas tablas en la base datos empresa
			
			System.out.println("Nombre base de datos: " + nombre);
			System.out.println("Driver de conexion: " + driver);
			System.out.println("URL de alojamiento de la base de datos: " + URL);
			System.out.println("Nombre usuario : " + nombreUsu);
			
			while (tablas.next()) {//mientras haya tablas mostramos el  nombre de cada una.
				System.out.println("Nombre tablas: "+ tablas.getString("TABLE_NAME"));
			}
			
			ResultSet tablaEmpleado = dbmd.getColumns(null, "empresa", "empleados", null);//para la tabla empleados de empresa obtiene informacion sobre columnas.
			ResultSet tablaEmpleadoPK = dbmd.getPrimaryKeys(null,"empresa",  "empleados");//para la tabla empleados de empresa obtiene informacion sobre las columnas que son clave primaria.
			ResultSet tablaEmpleadoFK = dbmd.getExportedKeys(null,"empresa",  "empleados");//devuelve lista de claves ajenas que usan la clave primaria de la tabla.
			ResultSet tablaEmpleadoFK2 = dbmd.getImportedKeys(null,"empresa",  "empleados");
			System.out.println("Datos de la tabla empleados");
			
			while (tablaEmpleado.next()) {
				
				System.out.println("\t"+tablaEmpleado.getString("COLUMN_NAME") +" "+ tablaEmpleado.getString("TYPE_NAME")+" "+ tablaEmpleado.getString("COLUMN_SIZE"));
				
				while (tablaEmpleadoPK.next()){
					System.out.println("\tClave Primaria: "+tablaEmpleadoPK.getString("COLUMN_NAME"));
				}
				while (tablaEmpleadoFK.next()){
					System.out.println("\tClave Ajena: "+tablaEmpleadoFK.getString("COLUMN_NAME"));
				}
			}
			while (tablaEmpleadoFK2.next()){
				System.out.println("\tClave Ajena: "+tablaEmpleadoFK2.getString("COLUMN_NAME"));
			}
		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (InstantiationException ie) {
			ie.printStackTrace();
		} catch (IllegalAccessException ia) {
			ia.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
