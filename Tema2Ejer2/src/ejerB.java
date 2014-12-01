import java.sql.*;

public class ejerB {
	public static void main(String[] args) {
		try {
			
			Class.forName("org.sqlite.JDBC").newInstance();// Cargar el driver, se le pasa este driver JDBC para SQLite
			Connection conexion = DriverManager.getConnection("jdbc:sqlite:c:/sqlite/empresas.db");//establecemos conexion con la bd
			Statement sentencia = conexion.createStatement();//creamos la sentencia
			ResultSet resul = sentencia.executeQuery("SELECT * FROM empleados WHERE salario>=2100");//creamos y realizamos la consulta
			while (resul.next()) {//mientras no sea el siguiente al ultimo
				System.out.println("Número de empleado: "+resul.getInt(1) + " Apellido:" + resul.getString(2)	+ " Profesión: " + resul.getString(3) + " Fecha de alta: "+ 
						resul.getString(4)+ " Salario: "+ resul.getInt(5)+ " Comisión"+ resul.getInt(6)+ " Número departamento: "+ resul.getInt(7));
			}
			resul.close();//cerramos la consulta
			sentencia.close();//cerramos la sentencia
			conexion.close();//cerramos la conexion con la bd
		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (InstantiationException ie) {
			ie.printStackTrace();
		} catch (IllegalAccessException ia) {
			ia.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// fin del main
}