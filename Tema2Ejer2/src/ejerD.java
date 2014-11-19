import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class ejerD {
	public static void main(String[] args) {
		try {
			Scanner teclado = new Scanner(System.in);
			int numeroEmpleado;
			Class.forName("org.sqlite.JDBC").newInstance();// Cargar el driver,
															// se le pasa este
															// driver JDBC para
															// SQLite
			Connection conexion = DriverManager
					.getConnection("jdbc:sqlite:c:/sqlite/empresas.db");// establecemos
																		// conexion
																		// con
																		// la bd
			Statement sentencia = conexion.createStatement();// creamos la
																// sentencia

																	// realizamos
																	// la
																	// consulta
			// creamos
																		// y
																		// realizamos
																		// la
																		// consulta
			
			
			System.out.print("introduzca emp_no: ");
			numeroEmpleado = teclado.nextInt();
			ResultSet resulEmpleados = sentencia
					.executeQuery("select apellido from empleados where empleados.emp_no='" + String.valueOf(numeroEmpleado)+"'");
			
			while (!resulEmpleados.next()) {
				
					System.out.print("introduzca emp_no de nuevo: ");
					numeroEmpleado = teclado.nextInt();
					ResultSet resulEmpleados2 = sentencia
							.executeQuery("select apellido from empleados where empleados.emp_no='" + String.valueOf(numeroEmpleado)+"'");
			}
			ResultSet resul = sentencia.executeQuery("SELECT * FROM empleados where empleados.emp_no='" + String.valueOf(numeroEmpleado)+"'");
			while (resul.next()) {//mientras no sea el siguiente al ultimo
				System.out.println(resul.getInt(1) + " " + resul.getString(2)	+ " " + resul.getString(3) + " "+ resul.getString(4)+ " "+ resul.getInt(5)+ " "+ resul.getInt(6)+ " "+ resul.getInt(7));
			}
			teclado.nextLine();
			System.out.println(" ");
			System.out.println("Esta seguro que desea borrar el empleado(S/N) " +numeroEmpleado );
			String confirmacion;
			confirmacion=teclado.nextLine();
			if(confirmacion.equalsIgnoreCase("S")){
				int resul2 = sentencia.executeUpdate("delete from empleados where empleados.emp_no='" + String.valueOf(numeroEmpleado)+"'");
				ResultSet resul3 = sentencia
						.executeQuery("SELECT * FROM empleados");
				while (resul3.next()) {//mientras no sea el siguiente al ultimo
					System.out.println(resul.getInt(1) + " " + resul.getString(2)	+ " " + resul.getString(3) + " "+ resul.getString(4)+ " "+ resul.getInt(5)+ " "+ resul.getInt(6)+ " "+ resul.getInt(7));
				}
			}
			else{
				System.out.println("El empleado " +numeroEmpleado+ " no ha sido borrado");
			}
			
			
			
			sentencia.close();// cerramos la sentencia
			conexion.close();// cerramos la conexion con la bd
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
