//Clase para resolver los ejercicios 7, 8 y 9
package ejerFernando;
import java.io.*;
import java.sql.*;

public class Ejercicio5 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		InputStreamReader isr= new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader (isr);
		Connection conexion=null;
		int op, op1;
		op=menu(br);
		do{
			switch(op){
			case 1:
				System.out.println("Ha elegido trabajar con MYSQL");
				try
				{
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					conexion=DriverManager.getConnection("jdbc:mysql://localhost/acceso", "root", "root");
				}catch (ClassNotFoundException e){ e.printStackTrace();} 
				catch (SQLException e) {e.printStackTrace();} 
				catch (InstantiationException e) {e.printStackTrace();} 
				catch (IllegalAccessException e) {e.printStackTrace();}
				
				acciones(conexion);
				op=menu(br);
				break;
			case 2:
				System.out.println("Ha elegido trabajar con SQLite");
				try
				{
					Class.forName("org.sqlite.JDBC").newInstance();
					conexion=DriverManager.getConnection("jdbc:sqlite:c:/sqlite/ventas.db");
				}catch (ClassNotFoundException e){ e.printStackTrace();} 
				catch (SQLException e) {e.printStackTrace();}
				catch (InstantiationException e) {e.printStackTrace();} 
				catch (IllegalAccessException e) {e.printStackTrace();}
				
				acciones(conexion);
				op=menu(br);
				break;
			case 3:
				System.out.println("Finalizada la ejecución");
				break;
			}	
		
		}while(op!=3);
	
	}//fin del main
	
	/**
	 * Método InsertarProductos
	 */
	private static void InsertarProductos(Connection conexion){
		InputStreamReader isr= new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader (isr);
		int id;
		String sql="INSERT INTO productos VALUES (?, ?, ?, ?, ?) ";
		String sql1="SELECT * FROM productos WHERE (id=?)";
		try {
			PreparedStatement sentencia=conexion.prepareStatement(sql);
			PreparedStatement sent1=conexion.prepareStatement(sql1);
			System.out.println("==================");
			System.out.println("INSERTAR PRODUCTOS");
			System.out.println("==================");
			id = ComprobarProducto(sent1);
			while (id!=0){
				System.out.print("Descripción del producto: ");
				String des=br.readLine();
				System.out.print("Stock actual:");
				int stact=Integer.parseInt(br.readLine());
				System.out.print("Stock mínimo:");
				int stmin=Integer.parseInt(br.readLine());
				System.out.print("PVP:");
				float pvp=Float.parseFloat(br.readLine());
				sentencia.setInt(1,id);
				sentencia.setString(2, des);
				sentencia.setInt(3, stact);
				sentencia.setInt(4, stmin);
				sentencia.setFloat(5, pvp);
				sentencia.executeUpdate();
				id = ComprobarProducto(sent1);
			}
		} catch (SQLException e) {e.printStackTrace();} 
		catch (NumberFormatException e) {e.printStackTrace();} 
		catch (IOException e) {e.printStackTrace();}
		
	}
	/**
	 * Método InsertarProductos
	 */
	private static void InsertarClientes(Connection conexion){
		InputStreamReader isr= new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader (isr);
		int id;
		String sql="INSERT INTO clientes VALUES (?, ?, ?, ?, ?,?) ";
		String sql1="SELECT * FROM clientes WHERE (id=?)";
		try {
			PreparedStatement sentencia=conexion.prepareStatement(sql);
			PreparedStatement sent1=conexion.prepareStatement(sql1);
			System.out.println("==================");
			System.out.println("INSERTAR CLIENTES");
			System.out.println("==================");
			id = ComprobarCliente(sent1);
			while (id!=0){
				System.out.print("Nombre del cliente: ");
				String nom=br.readLine();
				System.out.print("Dirección del cliente:");
				String dir=br.readLine();
			
				System.out.print("Stock mínimo:");
				int stmin=Integer.parseInt(br.readLine());
				System.out.print("PVP:");
				float pvp=Float.parseFloat(br.readLine());
				sentencia.setInt(1,id);
				sentencia.setString(2, nom);
				sentencia.setString(3, dir);
				sentencia.setInt(4, stmin);
				sentencia.setFloat(5, pvp);
				sentencia.executeUpdate();
				id = ComprobarProducto(sent1);
			}
		} catch (SQLException e) {e.printStackTrace();} 
		catch (NumberFormatException e) {e.printStackTrace();} 
		catch (IOException e) {e.printStackTrace();}
		
	}
	/**
	 * Solicitar el id del producto y comprobar que no existe en la tabla
	 * @param sent1 
	 * @return id, valor del id del producto, validado o 0 para salir
	 * @throws IOException
	 * @throws SQLException
	 */
	private static int ComprobarProducto(PreparedStatement sent1) throws IOException, SQLException {
		InputStreamReader isr= new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader (isr);
		int id;
		ResultSet resulSent1=null;
		System.out.print("Código del producto: ");
		id=Integer.parseInt(br.readLine());
		sent1.setInt(1, id);
		resulSent1=sent1.executeQuery();
		while(resulSent1.next()){
				System.out.println("El producto existe");
				System.out.print("Código del producto: ");
				id=Integer.parseInt(br.readLine());
				sent1.setInt(1, id);
				resulSent1=sent1.executeQuery();
			}
		resulSent1.close();
		return id;
	}
	
	/**
	 * Solicitar el id del cliente y comprobar que no existe en la tabla
	 * @param sent1 
	 * @return id, valor del id del cliente, validado o 0 para salir
	 * @throws IOException
	 * @throws SQLException
	 */
	private static int ComprobarCliente(PreparedStatement sent1) throws IOException, SQLException {
		InputStreamReader isr= new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader (isr);
		int id;
		ResultSet resulSent1=null;
		System.out.print("Código del cliente: ");
		id=Integer.parseInt(br.readLine());
		sent1.setInt(1, id);
		resulSent1=sent1.executeQuery();
		while(resulSent1.next()){
				System.out.println("El cliente ya existe");
				System.out.print("Código del cliente: ");
				id=Integer.parseInt(br.readLine());
				sent1.setInt(1, id);
				resulSent1=sent1.executeQuery();
			}
		resulSent1.close();
		return id;
	}
	/**
	 * Método ListarProductos
	 */
	private static void ListarProductos(Connection conexion){
		String sql="SELECT * from productos ";
		try {
			Statement sentencia=conexion.createStatement();
			ResultSet consulta=sentencia.executeQuery(sql);
			System.out.println("====================");
			System.out.println("LISTADO DE PRODUCTOS");
			System.out.println("====================");
			while (consulta.next()){
				System.out.println("ID del producto: "+consulta.getInt(1));
				System.out.println("Descripción del producto: "+ consulta.getString(2));
				System.out.println("Stock actual:"+consulta.getInt(3));
				System.out.println("Stock mínimo:"+consulta.getInt(4));
				System.out.println("PVP:"+consulta.getFloat(5));
				System.out.println("----------------------");
			}
		} catch (SQLException e) {e.printStackTrace();} 
		catch (NumberFormatException e) {e.printStackTrace();}
		
	}
	/**
	 * Método para mostrar el menú principal
	 * @param br
	 * @return
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	private static int menu(BufferedReader br) throws NumberFormatException, IOException{
		int op;
		System.out.println("MENU PRINCIPAL");
		System.out.println("1. Trabajar con MYSQL");
		System.out.println("2. Trabajar con SQLite");
		System.out.println("3. Salir");
		System.out.print("Elegir una opción (1-3): ");
		op=Integer.parseInt(br.readLine());
		while (op<1 && op>3){
			System.out.print("Elegir una opción (1-3): ");
			op=Integer.parseInt(br.readLine());
		}
		return op;
	}
	
	/**
	 * Método para mostrar las opciones de trabajo sobre la base de datos
	 * @param br
	 * @return
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	private static int opciones() throws NumberFormatException, IOException{
		InputStreamReader isr= new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader (isr);
		int op1;
		System.out.println("OPCIONES SOBRE LA BASE DE DATOS");
		System.out.println("1. Insertar PRODUCTOS");
		System.out.println("2. Insertar CLIENTES");
		System.out.println("3. Listar  PRODUCTOS");
		System.out.println("4. Listar CLIENTES");
		System.out.println("5. Insertar VENTAS");
		System.out.println("0. Volver a atrás");
		System.out.print("Elegir una opción (0-5): ");
		op1=Integer.parseInt(br.readLine());
		while (op1<0 && op1>5){
			System.out.print("Elegir una opción (0-5): ");
			op1=Integer.parseInt(br.readLine());
		}
		return op1;
	}// Fin método opciones
	
	private static void acciones(Connection conexion) throws NumberFormatException, IOException{
		int opc=opciones();
		do{
			switch(opc){
			case 1:
				InsertarProductos(conexion);
				opc=opciones();
				break;
			case 2:
				InsertarClientes(conexion);
				opc=opciones();
				break;
			case 3:
				ListarProductos(conexion);
				opc=opciones();
				break;
			case 4:
				System.out.println("Ha elegido LISTAR CLIENTES");
				opc=opciones();
				break;
			case 5:
				System.out.println("Ha elegido INSERTAR VENTAS");
				opc=opciones();
				break;
			case 0:
				System.out.println("Ha elegido VOLVER A ATRAS");
				break;
			}
		
		}while(opc!=0);
	}//Fin método acciones
	

	
}
