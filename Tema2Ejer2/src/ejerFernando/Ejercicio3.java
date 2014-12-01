package ejerFernando;
import java.sql.*;

public class Ejercicio3 {
	public static void main(String[]args){
		try{
			//Carga del driver
			Class.forName("com.mysql.jdbc.Driver");
			Connection conex=DriverManager.getConnection("jdbc:mysql://localhost/tienda","root","root");
			DatabaseMetaData dbmd=conex.getMetaData();
			System.out.println("INFORMACION DE LA BASE DE DATOS MYSQL");
			System.out.println("Nombre de la base de datos: "+dbmd.getDatabaseProductName());
			System.out.println("Driver de la conexion: "+dbmd.getDriverName());
			System.out.println("URL alojamiento de la base de datos: "+dbmd.getURL());
			System.out.println("Nombre del usuario: "+dbmd.getUserName());
			System.out.println("------------------------------------------");
			ResultSet tablas=dbmd.getTables(null, "ejercicios", null, null);
			while (tablas.next()) {
				//Mostrar las tablas y vistas en el esquema ejercicios
				System.out.println("Tablas y vistas en ejercicios: "
						+ tablas.getString("TABLE_NAME"));
				String tab1=tablas.getString("TABLE_NAME");
				
				//Mostrar la información sobre las columnas de una tabla o tablas.
				ResultSet tab = dbmd.getColumns(null, "ejercicios",tab1, null);
				while (tab.next()) {
						System.out.println(
							"Campo:"+ tab.getString("COLUMN_NAME")
							+ " Tipo: "+tab.getString("TYPE_NAME")
							+ " Tamaño: "+tab.getString("COLUMN_SIZE"));
				}
				
				//Mostrar la clave primaria de una tabla
				ResultSet pk=dbmd.getPrimaryKeys(null, "ejercicios", tab1);
				String pkDep=" ", separador= " ";
				while (pk.next()){
					pkDep=pkDep+separador+pk.getString("COLUMN_NAME");
				}
				System.out.println("Clave primaria: "+pkDep);
				
				//Mostrar las claves secundarias de una tabla
				ResultSet fk=dbmd.getExportedKeys(null, "ejercicios", tab1);
				while (fk.next()){
					System.out.println("Tabla FK: " + fk.getString("FKTABLE_NAME")+ " Clave ajena: "+ fk.getString("FKCOLUMN_NAME"));
					}
				System.out.println("------------------------------------------");
			}
			
			tablas.close();
			conex.close();			
		}
		catch (ClassNotFoundException cn){cn.printStackTrace();}
		catch (SQLException sqe) {sqe.printStackTrace();}
		
	} // fin de static

}// fin de class

