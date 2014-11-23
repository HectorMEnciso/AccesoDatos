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
		int opcion, stockActual, stockMinimo, pvp, opcionTablas,idCliente,idProducto,cantidad;
		String id, descripcion, nombre, direccion, poblacion, telef, nif,fecha;
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
					System.out.println("3-.VENTAS");
					System.out.println("4-.LISTADO CLIENTES");
					System.out.println("5-.NINGUNA");
					System.out.print("Seleccione opcion: ");
					opcionTablas = teclado.nextInt();
					teclado.nextLine();
					if (opcionTablas == 1) {
						try {
							Connection conexion = DriverManager.getConnection("jdbc:sqlite:c:/sqlite/tienda.db");//Establecemos conexion BBDD SQLITE
							Statement sentencia = conexion.createStatement();
							Class.forName("org.sqlite.JDBC").newInstance();

							System.out.println("Introducion datos tabla PRODUCTOS SQLITE");
							System.out.println("==================================");
							//do{
							System.out.print("Introduzca id: ");
							id = teclado.nextLine();
						
							while (id.equals("")) {//Mientras DNI se nulo, volvemos a pedirlo
								System.out.print("El campo no puede ser nulo, Introduzca id: ");
								id = teclado.nextLine();
							}
							ResultSet resulDepartamentos = sentencia.executeQuery("SELECT id FROM productos where productos.id='"+ String.valueOf(Integer.parseInt(id)) + "'");//Crea un Resulset con los produztos cuyo id sea = al introducido
							
							while (resulDepartamentos.next()) {//Si nos permite avanzar, significa que existe, mientras exista lo solicita, pues no puede haber dos productos con el mismo ID

								System.out.print("El id del producto ya existe: ");
								id = teclado.nextLine();
								ResultSet resulDepartamentos2 = sentencia.executeQuery("SELECT id FROM productos where productos.id='"+ String.valueOf(Integer.parseInt(id)) + "'");//????
							}
							System.out.print("Introduzca descripcion: ");
							descripcion = teclado.nextLine();
							while (descripcion.equals("")) {
								System.out.print("El campo no puede ser nulo, Introduzca descripcion: ");
								descripcion = teclado.nextLine();
							}
							System.out.print("Introduzca stock actual: ");
							stockActual = teclado.nextInt();
							System.out.print("Introduzca stock minimo: ");
							stockMinimo = teclado.nextInt();
							System.out.print("Introduzca pvp: ");
							pvp = teclado.nextInt();
							teclado.nextLine();
							Statement sentencia1 = conexion.createStatement();// creamos sentencia
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
							ResultSet resul = sentencia.executeQuery("SELECT * FROM productos");
							while (resul.next()) {// mientras no sea el siguiente al ultimo
								System.out.println(resul.getInt(1) + " "
										+ resul.getString(2) + " "
										+ resul.getInt(3) + resul.getInt(5));
							}
							//}while(!id.equals("0"));
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
							
							Connection conexion = DriverManager.getConnection("jdbc:sqlite:c:/sqlite/tienda.db");
							Statement sentencia = conexion.createStatement();
							Class.forName("org.sqlite.JDBC").newInstance();

							System.out.println("introducion datos tabla CLIENTES SQLITE");
							System.out.println("==================================");
					System.out.print("Introduzca id: ");
					id = teclado.nextLine();
					while (id.equals("")) {
						System.out.print("El campo no puede ser nulo, Introduzca id: ");
						id = teclado.nextLine();
					}
					ResultSet resulClientes = sentencia.executeQuery("SELECT id FROM clientes where clientes.id='"+ String.valueOf(Integer.parseInt(id)) + "'");
					while (resulClientes.next()) {

						System.out.print("El id del cliente ya existe: ");
						id = teclado.nextLine();
						ResultSet resulClientes2 = sentencia.executeQuery("SELECT id FROM clientes where clientes.id='"+ String.valueOf(Integer.parseInt(id)) + "'");
					}
					System.out.print("Introduzca nombre: ");
					nombre = teclado.nextLine();
					while (nombre.equals("")) {
						System.out.print("El campo no puede ser nulo, Introduzca nombre: ");
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

					Statement sentenciaClientes = conexion.createStatement();
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
					ResultSet resulCliente = sentencia.executeQuery("SELECT * FROM clientes");
					while (resulCliente.next()) {
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
			}
					else if(opcionTablas==3){
						try{
							
							Connection conexion = DriverManager.getConnection("jdbc:sqlite:c:/sqlite/tienda.db");
							Statement sentencia = conexion.createStatement();
							Class.forName("org.sqlite.JDBC").newInstance();
							
							System.out.println("Introducion datos tabla VENTAS SQLITE");
							System.out.println("==================================");
							System.out.print("Introduzca id DE VENTA: ");
							id = teclado.nextLine();
							
							while (id.equals("")) {
								System.out.print("El campo no puede ser nulo, Introduzca id VENTA: ");
								id = teclado.nextLine();
							}
							ResultSet resulVentas = sentencia.executeQuery("SELECT IDVENTA FROM ventas where ventas.IDVENTA='"+ String.valueOf(Integer.parseInt(id)) + "'");
							while (resulVentas.next()) {

								System.out.print("El id VENTA ya existe ya existe: ");
								id = teclado.nextLine();
								ResultSet resulVentas2 = sentencia.executeQuery("SELECT IDVENTA FROM ventas where ventas.IDVENTA='"+ String.valueOf(Integer.parseInt(id)) + "'");
							}
							System.out.println("Introduzca fecha: ");
							fecha=teclado.nextLine();
							
							while (fecha.equals("")) {
								System.out.print("El campo no puede ser nulo, Introduzca fecha: ");
								fecha = teclado.nextLine();
							}
							System.out.println("Introduzca ID de Cliente");
							idCliente=teclado.nextInt();
							ResultSet resulIdCliente= sentencia.executeQuery("SELECT id FROM clientes where clientes.id='"+ String.valueOf(idCliente) + "'");
							
							while (!resulIdCliente.next()) {

								System.out.print("El IdCliente no existe,introduzcalo de nuevo: ");
								idCliente = teclado.nextInt();
								ResultSet resulIdCliente2= sentencia.executeQuery("SELECT id FROM clientes where clientes.id='"+ String.valueOf(idCliente) + "'");
							}
							
							
							System.out.println("Introduzca ID de Producto");
							idProducto=teclado.nextInt();
							ResultSet resulIdProducto= sentencia.executeQuery("SELECT id FROM productos where productos.id='"+ String.valueOf(idProducto) + "'");
							
							while (!resulIdProducto.next()) {

								System.out.print("El IdProducto no existe,introduzcalo de nuevo: ");
								idProducto = teclado.nextInt();
								ResultSet resulIdProducto2= sentencia.executeQuery("SELECT id FROM productos where productos.id='"+ String.valueOf(idProducto) + "'");
							}
							System.out.println("Introduzca cantidad: ");
							cantidad=teclado.nextInt();
							while(cantidad<1){
								System.out.println("La cantidad debe ser mayor que 0: ");
								cantidad=teclado.nextInt();
							}
							ResultSet resulStock= sentencia.executeQuery("SELECT stockactual FROM productos where productos.id='"+ String.valueOf(idProducto) + "'");
							
							
							
							Statement sentenciaVentas = conexion.createStatement();
							int resul1Ventas= sentenciaVentas.executeUpdate("insert into ventas values( "
											+ "'"
											+ Integer.parseInt(id)
											+ "','"
											+ fecha
											+ "','"
											+ idCliente
											+ "','"
											+ idProducto
											+ "','"
											+ cantidad
											+ "')");
							ResultSet resulTablaVentas = sentencia.executeQuery("SELECT * FROM ventas");
							while (resulTablaVentas.next()) {
								System.out.println(resulTablaVentas.getInt(1) + " "
										+ resulTablaVentas.getString(2) + " "
										+ resulTablaVentas.getInt(3) + " "
										+ resulTablaVentas.getInt(4) + " "
										+ resulTablaVentas.getInt(5));
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
					else if(opcionTablas==4){
						try{
						Connection conexion = DriverManager.getConnection("jdbc:sqlite:c:/sqlite/tienda.db");
						Statement sentencia = conexion.createStatement();
						Class.forName("org.sqlite.JDBC").newInstance();
						
						System.out.println("LISTADO CLIENTES TABLA VENTAS");
						System.out.println("==============================");
						
						System.out.println("Introduzca ID de cliente para listar sus ventas: ");
						idCliente=teclado.nextInt();
						ResultSet resulIdCliente= sentencia.executeQuery("SELECT id FROM clientes where clientes.id='"+ String.valueOf(idCliente) + "'");
						
						while (!resulIdCliente.next()) {

							System.out.print("El IdCliente no existe,introduzcalo de nuevo: ");
							idCliente = teclado.nextInt();
							ResultSet resulIdCliente2= sentencia.executeQuery("SELECT id FROM clientes where clientes.id='"+ String.valueOf(idCliente) + "'");
						}
						
						ResultSet resulListado= sentencia.executeQuery("SELECT * FROM ventas where ventas.idcliente='"+ String.valueOf(idCliente) + "'");
						
						while (resulListado.next()) {
							System.out.println(resulListado.getInt(1) + " "
									+ resulListado.getString(2) + " "
									+ resulListado.getInt(3) + " "
									+ resulListado.getInt(4) + " "
									+ resulListado.getInt(5));
						}
						//System.out.println("El cliente con ID: "+idCliente + "ha realizado "+resulListado.getInt(1)+" compras");
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
					else {
						System.out.println("No ha elegido ninguna tabla");
					}
				} while (opcionTablas != 5);

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
							
							Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/tienda", "root","root");
							Statement sentenciaMysql = conexion.createStatement();
							Class.forName("com.mysql.jdbc.Driver").newInstance();
							//Class.forName("org.sqlite.JDBC").newInstance();

							System.out.println("Introducion datos tabla PRODUCTOS MYSQL");
							System.out.println("==================================");
							System.out.print("Introduzca id: ");
							id = teclado.nextLine();
							while (id.equals("")) {
								System.out.print("El campo no puede ser nulo, Introduzca id: ");
								id = teclado.nextLine();
							}
							ResultSet resulProducto = sentenciaMysql.executeQuery("SELECT id FROM productos where productos.id='"+ String.valueOf(Integer.parseInt(id)) + "'");
							while (resulProducto.next()) {

								System.out.print("El id del producto ya existe: ");
								id = teclado.nextLine();
								ResultSet resulProducto2 = sentenciaMysql.executeQuery("SELECT id FROM productos where productos.id='"+ String.valueOf(Integer.parseInt(id)) + "'");
							}
							
							System.out.print("Introduzca descripcion: ");
							descripcion = teclado.nextLine();
							while (descripcion.equals("")) {
								System.out.print("El campo no puede ser nulo, Introduzca descripcion: ");
								descripcion = teclado.nextLine();
							}
							System.out.print("Introduzca stock actual: ");
							stockActual = teclado.nextInt();
							System.out.print("Introduzca stock minimo: ");
							stockMinimo = teclado.nextInt();
							System.out.print("Introduzca pvp: ");
							pvp = teclado.nextInt();

							Statement sentencia1 = conexion.createStatement();
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
							ResultSet resul = sentenciaMysql.executeQuery("SELECT * FROM productos");
							while (resul.next()) {
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
							Class.forName("com.mysql.jdbc.Driver").newInstance();
							Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/tienda", "root","root");
							Statement sentencia = conexion.createStatement();

							System.out.println("introducion datos tabla CLIENTES MYSQL");
							System.out.println("==================================");
							System.out.print("Introduzca id: ");
							id = teclado.nextLine();
							while (id.equals("")) {
								System.out.print("El campo no puede ser nulo, Introduzca id: ");
								id = teclado.nextLine();
							}
							ResultSet resulClientes = sentencia.executeQuery("SELECT id FROM clientes where clientes.id='"+ String.valueOf(Integer.parseInt(id)) + "'");
							while (resulClientes.next()) {

								System.out.print("El id del cliente ya existe: ");
								id = teclado.nextLine();
								ResultSet resulClientes2 = sentencia.executeQuery("SELECT id FROM clientes where clientes.id='"+ String.valueOf(Integer.parseInt(id)) + "'");
							}
							System.out.print("Introduzca nombre: ");
							nombre = teclado.nextLine();
							while (nombre.equals("")) {
								System.out.print("El campo no puede ser nulo, Introduzca nombre: ");
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

							Statement sentenciaClientes = conexion.createStatement();
						
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
							ResultSet resulCliente = sentencia.executeQuery("SELECT * FROM clientes");
							while (resulCliente.next()) {
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
