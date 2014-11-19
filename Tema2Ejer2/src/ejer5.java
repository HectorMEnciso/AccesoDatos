import java.awt.font.NumericShaper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ejer5 {
	public static void main(String[] args) {
		int opcion, stockActual, stockMinimo, pvp, opcionTablas;
		String id, descripcion, nombre, direccion, poblacion, telef, nif;
		Scanner teclado = new Scanner(System.in);
		do {
			System.out.println("Seleccione BBDD: ");
			System.out.println("1-.SQLite");
			System.out.println("2-.MySQL");
			System.out.println("3-.Salir");
			System.out.print("Seleccione opcion: ");
			opcion = teclado.nextInt();
			teclado.nextLine();
			if (opcion == 1) {
				do {
					System.out.println("Seleccione tabla: ");
					System.out.println("1-.PRODUCTOS");
					System.out.println("2-.CLIENTES");
					System.out.println("3-.NINGUNA");
					System.out.print("Seleccione opcion: ");
					opcionTablas = teclado.nextInt();
					teclado.nextLine();
					if (opcionTablas == 1) {
						try {
							Connection conexion = DriverManager
									.getConnection("jdbc:sqlite:c:/sqlite/tienda.db");
							Statement sentencia = conexion.createStatement();

							Class.forName("org.sqlite.JDBC").newInstance();

							System.out
									.println("introducion datos tabla PRODUCTOS");
							do{
							System.out.print("Introduzca id: ");
							id = teclado.nextLine();
						
							while (id.equals("")) {
								System.out
										.print("El campo no puede ser nulo, Introduzca id: ");
								id = teclado.nextLine();
							}
							ResultSet resulDepartamentos = sentencia
									.executeQuery("SELECT id FROM productos where productos.id='"
											+ String.valueOf(Integer
													.parseInt(id)) + "'");
							while (resulDepartamentos.next()) {

								System.out
										.print("El id del producto ya existe: ");
								id = teclado.nextLine();
								ResultSet resulDepartamentos2 = sentencia
										.executeQuery("SELECT id FROM productos where productos.id='"
												+ String.valueOf(Integer
														.parseInt(id)) + "'");
							}
							System.out.print("Introduzca descripcion: ");
							descripcion = teclado.nextLine();
							while (descripcion.equals("")) {
								System.out
										.print("El campo no puede ser nulo, Introduzca descripcion: ");
								descripcion = teclado.nextLine();
							}
							System.out.print("Introduzca stock actual: ");
							stockActual = teclado.nextInt();
							System.out.print("Introduzca stock minimo: ");
							stockMinimo = teclado.nextInt();
							System.out.print("Introduzca pvp: ");
							pvp = teclado.nextInt();
							teclado.nextLine();
							Statement sentencia1 = conexion.createStatement();// creamos
																				// la
							// sentencia
							int resul1 = sentencia1
									.executeUpdate("insert into productos values( "
											+ "'"
											+ Integer.parseInt(id)
											+ "','"
											+ descripcion
											+ "','"
											+ stockActual
											+ "','"
											+ stockMinimo
											+ "','" + pvp + "')");
							ResultSet resul = sentencia
									.executeQuery("SELECT * FROM productos");
							while (resul.next()) {// mientras no sea el
													// siguiente al
													// ultimo
								System.out.println(resul.getInt(1) + " "
										+ resul.getString(2) + " "
										+ resul.getInt(3) + resul.getInt(5));
							}
							}while(!id.equals("0"));
						} catch (ClassNotFoundException cn) {
							cn.printStackTrace();
						} catch (InstantiationException ie) {
							ie.printStackTrace();
						} catch (IllegalAccessException ia) {
							ia.printStackTrace();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
					}else if (opcionTablas == 2) {
						
						try{
							Connection conexion = DriverManager
									.getConnection("jdbc:sqlite:c:/sqlite/tienda.db");
							Statement sentencia = conexion.createStatement();
							System.out
							.println("introducion datos tabla CLIENTES");

					System.out.print("Introduzca id: ");
					id = teclado.nextLine();
					while (id.equals("")) {
						System.out
								.print("El campo no puede ser nulo, Introduzca id: ");
						id = teclado.nextLine();
					}
					ResultSet resulClientes = sentencia
							.executeQuery("SELECT id FROM clientes where clientes.id='"
									+ String.valueOf(Integer
											.parseInt(id)) + "'");
					while (resulClientes.next()) {

						System.out
								.print("El id del cliente ya existe: ");
						id = teclado.nextLine();
						ResultSet resulClientes2 = sentencia
								.executeQuery("SELECT id FROM clientes where clientes.id='"
										+ String.valueOf(Integer
												.parseInt(id)) + "'");
					}
					System.out.print("Introduzca nombre: ");
					nombre = teclado.nextLine();
					while (nombre.equals("")) {
						System.out
								.print("El campo no puede ser nulo, Introduzca nombre: ");
						nombre = teclado.nextLine();
					}

					System.out.print("Introduzca direccion: ");
					direccion = teclado.nextLine();

					System.out.print("Introduzca poblacion: ");
					poblacion = teclado.nextLine();

					System.out.print("Introduzca telef: ");
					telef = teclado.nextLine();

					System.out.print("Introduzca nif: ");
					nif = teclado.nextLine();

					Statement sentenciaClientes = conexion
							.createStatement();// creamos
					// la
					// sentencia
					int resul1Clientes = sentenciaClientes
							.executeUpdate("insert into clientes values( "
									+ "'"
									+ Integer.parseInt(id)
									+ "','"
									+ nombre
									+ "','"
									+ direccion
									+ "','"
									+ poblacion
									+ "','"
									+ telef
									+ "','"
									+ nif
									+ "')");
					ResultSet resulCliente = sentencia
							.executeQuery("SELECT * FROM clientes");
					while (resulCliente.next()) {// mientras no sea el
													// siguiente al
													// ultimo
						System.out.println(resulCliente.getInt(1) + " "
								+ resulCliente.getString(2) + " "
								+ resulCliente.getString(3) + " "
								+ resulCliente.getString(4)
								+ resulCliente.getString(5) + " "
								+ resulCliente.getString(6));
					}
						
						
						} catch (SQLException e) {
							e.printStackTrace();
						}
			} else {
						System.out.println("No ha elegido ninguna tabla");
					}
				} while (opcionTablas != 3);

			} else if (opcion == 2) {
				do {
					System.out.println("Seleccione tabla: ");
					System.out.println("1-.PRODUCTOS");
					System.out.println("2-.CLIENTES");
					System.out.println("3-.NINGUNA");
					System.out.print("Seleccione opcion: ");
					opcionTablas = teclado.nextInt();
					teclado.nextLine();
					if (opcionTablas == 1) {

						try {
							Class.forName("com.mysql.jdbc.Driver")
									.newInstance();
							Connection conexion = DriverManager.getConnection(
									"jdbc:mysql://localhost/tienda", "root",
									"root");
							Statement sentencia = conexion.createStatement();

							Class.forName("org.sqlite.JDBC").newInstance();

							System.out
									.println("introducion datos tabla PRODUCTOS");
							System.out.print("Introduzca id: ");
							id = teclado.nextLine();
							while (id.equals("")) {
								System.out
										.print("El campo no puede ser nulo, Introduzca id: ");
								id = teclado.nextLine();
							}
							ResultSet resulDepartamentos = sentencia
									.executeQuery("SELECT id FROM productos where productos.id='"
											+ String.valueOf(Integer
													.parseInt(id)) + "'");
							while (resulDepartamentos.next()) {

								System.out
										.print("El id del producto ya existe: ");
								id = teclado.nextLine();
								ResultSet resulDepartamentos2 = sentencia
										.executeQuery("SELECT id FROM productos where productos.id='"
												+ String.valueOf(Integer
														.parseInt(id)) + "'");
							}
							System.out.print("Introduzca descripcion: ");
							descripcion = teclado.nextLine();
							while (descripcion.equals("")) {
								System.out
										.print("El campo no puede ser nulo, Introduzca descripcion: ");
								descripcion = teclado.nextLine();
							}
							System.out.print("Introduzca stock actual: ");
							stockActual = teclado.nextInt();
							System.out.print("Introduzca stock minimo: ");
							stockMinimo = teclado.nextInt();
							System.out.print("Introduzca pvp: ");
							pvp = teclado.nextInt();

							Statement sentencia1 = conexion.createStatement();// creamos
																				// la
							// sentencia
							int resul1 = sentencia1
									.executeUpdate("insert into productos values( "
											+ "'"
											+ Integer.parseInt(id)
											+ "','"
											+ descripcion
											+ "','"
											+ stockActual
											+ "','"
											+ stockMinimo
											+ "','" + pvp + "')");
							ResultSet resul = sentencia
									.executeQuery("SELECT * FROM productos");
							while (resul.next()) {// mientras no sea el
													// siguiente al
													// ultimo
								System.out.println(resul.getInt(1) + " "
										+ resul.getString(2) + " "
										+ resul.getInt(3) + resul.getInt(5));
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

					} else if (opcionTablas == 2) {

						try {
							Class.forName("com.mysql.jdbc.Driver")
									.newInstance();
							Connection conexion = DriverManager.getConnection(
									"jdbc:mysql://localhost/tienda", "root",
									"root");
							Statement sentencia = conexion.createStatement();

							System.out
									.println("introducion datos tabla CLIENTES");

							System.out.print("Introduzca id: ");
							id = teclado.nextLine();
							while (id.equals("")) {
								System.out
										.print("El campo no puede ser nulo, Introduzca id: ");
								id = teclado.nextLine();
							}
							ResultSet resulClientes = sentencia
									.executeQuery("SELECT id FROM clientes where clientes.id='"
											+ String.valueOf(Integer
													.parseInt(id)) + "'");
							while (resulClientes.next()) {

								System.out
										.print("El id del cliente ya existe: ");
								id = teclado.nextLine();
								ResultSet resulClientes2 = sentencia
										.executeQuery("SELECT id FROM clientes where clientes.id='"
												+ String.valueOf(Integer
														.parseInt(id)) + "'");
							}
							System.out.print("Introduzca nombre: ");
							nombre = teclado.nextLine();
							while (nombre.equals("")) {
								System.out
										.print("El campo no puede ser nulo, Introduzca nombre: ");
								nombre = teclado.nextLine();
							}

							System.out.print("Introduzca direccion: ");
							direccion = teclado.nextLine();

							System.out.print("Introduzca poblacion: ");
							poblacion = teclado.nextLine();

							System.out.print("Introduzca telef: ");
							telef = teclado.nextLine();

							System.out.print("Introduzca nif: ");
							nif = teclado.nextLine();

							Statement sentenciaClientes = conexion
									.createStatement();// creamos
							// la
							// sentencia
							int resul1Clientes = sentenciaClientes
									.executeUpdate("insert into clientes values( "
											+ "'"
											+ Integer.parseInt(id)
											+ "','"
											+ nombre
											+ "','"
											+ direccion
											+ "','"
											+ poblacion
											+ "','"
											+ telef
											+ "','"
											+ nif
											+ "')");
							ResultSet resulCliente = sentencia
									.executeQuery("SELECT * FROM clientes");
							while (resulCliente.next()) {// mientras no sea el
															// siguiente al
															// ultimo
								System.out.println(resulCliente.getInt(1) + " "
										+ resulCliente.getString(2) + " "
										+ resulCliente.getString(3) + " "
										+ resulCliente.getString(4)
										+ resulCliente.getString(5) + " "
										+ resulCliente.getString(6));
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
					} else {
						System.out.println("No ha elegido ninguna tabla.");
					}
				} while (opcionTablas != 3);
			} else {
				System.out.println("Ha salido del programa");
			}

		} while (opcion != 3);
	}
}
