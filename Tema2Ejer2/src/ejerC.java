import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ejerC {
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		String apellido, oficio, fecha_alt;
		int numeroEmpleado, salario, comision, deptNo;
		try {

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
			ResultSet resul = sentencia
					.executeQuery("SELECT emp_no FROM empleados");// creamos y
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
			ResultSet resulEmpleados = sentencia.executeQuery("select apellido from empleados where empleados.emp_no='" + String.valueOf(numeroEmpleado)+"'");
			
			while (resulEmpleados.next()) {
				
					System.out.print("introduzca emp_no de nuevo: ");
					numeroEmpleado = teclado.nextInt();
					ResultSet resulEmpleados2 = sentencia
							.executeQuery("select apellido from empleados where empleados.emp_no='" + String.valueOf(numeroEmpleado)+"'");
			}
			teclado.nextLine();
			System.out.print("introduzca apellido: ");
			apellido = teclado.nextLine();
			System.out.print("introduzca oficio:");
			oficio = teclado.nextLine();
			System.out.print("introduzca fecha_alt:");
			fecha_alt = teclado.nextLine();
			System.out.print("introduzca salario:");
			salario = teclado.nextInt();
			System.out.print("introduzca comision:");
			comision = teclado.nextInt();
			System.out.print("introduzca depNo:");
			deptNo = teclado.nextInt();
			ResultSet resulDepartamentos = sentencia
					.executeQuery("SELECT deptNo FROM departamentos where departamentos.deptNo='"+String.valueOf(deptNo)+"'");
			while (!resulDepartamentos.next()) {
				
					System.out.print("introduzca deptNo de nuevo: ");
					deptNo = teclado.nextInt();
					ResultSet resulDepartamentos2 = sentencia
							.executeQuery("SELECT deptNo FROM departamentos where departamentos.deptNo='"+String.valueOf(deptNo)+"'");
			}
			Statement sentencia1 = conexion.createStatement();// creamos la
																// sentencia
			int resul1 = sentencia1
					.executeUpdate("insert into empleados values( " + "'"
							+ numeroEmpleado + "','" + apellido + "','" + oficio
							+ "','" + fecha_alt + "','" + salario + "','"
							+ comision + "','" + deptNo + "')");// creamos y
																// realizamos la
																// consulta
			while (resul.next()) {// mientras no sea el siguiente al ultimo
				System.out.println(resul.getInt(1) + " " + resul.getString(2)
						+ " " + resul.getString(3) + " " + resul.getString(4)
						+ " " + resul.getInt(5) + " " + resul.getInt(6) + " "
						+ resul.getInt(7));
			}
			resul.close();// cerramos la consulta
			resulDepartamentos.close();// cerramos la consulta
			sentencia1.close();// cerramos la sentencia
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
	}// fin del main
}