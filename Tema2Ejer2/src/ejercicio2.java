import java.util.Scanner;
import java.sql.*;

public class ejercicio2 {
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		int op = 0;
		try {
			// Cargar el driver
			Class.forName("org.sqlite.JDBC").newInstance();
			Connection con = null;
			Statement sen = null;
			ResultSet res = null;
			con = DriverManager.getConnection("jdbc:sqlite:c:/sqlite/empresas.db");
			sen = con.createStatement();
			do {

				switch (op) {
				case 1:
					String sql = "SELECT * FROM empleados";
					res = sen.executeQuery(sql);
					while (res.next()) {
						System.out.println("Nº empleado: " + res.getInt(1));
						System.out.println("Apellidos: " + res.getString(2));
						System.out.println("Oficio: " + res.getString(3));
						System.out.println("Fecha alta: " + res.getString(4));
						System.out.println("Salario: " + res.getString(5));
						System.out.println("Comisión: " + res.getString(6));
						System.out.println("Departamento: " + res.getInt(7));
						System.out.println("");
					}
					break;

				case 2:
					String sql1 = "SELECT * FROM empleados where salario >= 1000";
					res = sen.executeQuery(sql1);
					while (res.next()) {
						System.out.println("Nº empleado: " + res.getInt(1));
						System.out.println("Apellidos: " + res.getString(2));
						System.out.println("Oficio: " + res.getString(3));
						System.out.println("Fecha alta: " + res.getString(4));
						System.out.println("Salario: " + res.getString(5));
						System.out.println("Comisión: " + res.getString(6));
						System.out.println("Departamento: " + res.getInt(7));
						System.out.println("");
					}
					break;
				case 3:
					altaEmpleado1(teclado, sen);
					break;
				}
				op = menu(teclado);
			} while (op != 0);

		}

		catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (InstantiationException ie) {
			ie.printStackTrace();
		} catch (IllegalAccessException ia) {
			ia.printStackTrace();
		} catch (SQLException sqe) {
			sqe.printStackTrace();
		}
	}// fin del main


	private static void altaEmpleado1(Scanner teclado, Statement sen)throws SQLException {
		
		int emp, dep;
		ResultSet res3 = null;
		ResultSet res4 = null;
		
		System.out.println("Introducir empleados en la base");
		System.out.print("Codigo del empleado: ");
		emp = teclado.nextInt();
		
		while (emp != 0) {
			String sql3 = "SELECT * FROM empleados WHERE emp_no="+ Integer.toString(emp);
			res3 = sen.executeQuery(sql3);

			while (res3.next()) {
				System.out.println("Este empleado ya existe");
				System.out.print("Introduzca nuevo codigo del empleado: ");
				emp = teclado.nextInt();
				//3
				//res3 = sen.executeQuery(sql3);
			}

				System.out.print("Código del departamento: ");
				dep = teclado.nextInt();
				
				res4 = sen.executeQuery("SELECT * FROM departamentos WHERE deptNo="+ Integer.toString(dep));

				while (!res4.next()) {
					System.out.println("El departamento del empleado no existe");
					System.out.print("Vuelva a introducir código del departamento: ");
					dep = teclado.nextInt();
					res4 = sen.executeQuery("SELECT * FROM departamentos WHERE deptNo="+ Integer.toString(dep));
				}

			System.out.print("Apellidos: ");
			String apell = teclado.next();
			System.out.print("Oficio: ");
			String ofic = teclado.next();
			System.out.print("Fecha alta: ");
			String fec = teclado.next();
			System.out.print("Salario: ");
			int sala = teclado.nextInt();
			System.out.print("Comisión: ");
			int comi = teclado.nextInt();
			String sql4 = "INSERT INTO empleados VALUES ('" + emp + "','"
					+ apell + "','" + ofic + "','" + fec + "','" + sala + "','"
					+ comi + "','" + dep + "')";
			int nfilasin = sen.executeUpdate(sql4);
			System.out.println("n filas insertadas: " + nfilasin);
			System.out.print("Codigo del empleado: ");
			emp=teclado.nextInt();
		}
	}

	private static int menu(Scanner teclado) {
		int op;
		System.out.println("MENU PARA INTERACTUAR CON LA BASE DE DATOS empresas.db");
		System.out.println("1.- Todos los empleados");
		System.out.println("2.- Empleados con salario >= 1000");
		System.out.println("3.- Insertar datos de empleados ");
		System.out.println("4.- Eliminar un empleado");
		System.out.println("0.- Salir");
		System.out.print("Elegir una opcion:");
		op = teclado.nextInt();
		return op;
	} // fin del menu
}