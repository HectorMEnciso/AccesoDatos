import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class t2e5jdbc {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		int base, tabla, id, stockminimo, stockactual, idproducto, idcliente, cantidad,stock =0,numventas=0,canttotal=0;
		String descripcion, nombre, direccion, poblacion, telefono, nif, fechaventa;
		float pvp,total=0;
		boolean existe = false, ventanula=false, hayventas=false;
		Connection conexion = null;
		Statement sentencia = null;

		System.out.println("MENÚ DE INSERCIÓN EN BASE DE DATOS TIENDA");
		System.out.println("1.- MySQL");
		System.out.println("2.- SQLite");
		System.out.println("3.- Salir");
		System.out.print("Seleccione la base de datos: ");
		base = teclado.nextInt();
		do {

			try {
				if(base==1){
					Class.forName("com.mysql.jdbc.Driver");
					 conexion=DriverManager.getConnection("jdbc:mysql://localhost/tienda", "root", "root");
				}
				else if(base==2){
					Class.forName("org.sqlite.JDBC").newInstance();
					 conexion = DriverManager.getConnection("jdbc:sqlite:c:/sqlite/tienda.db");
				}
				
				System.out.println("MENU DE OPCIONES");
				System.out.println("1.- Insertar Producto");
				System.out.println("2.- Insertar Cliente");
				System.out.println("3.- Insertar Venta");
				System.out.println("4.- Listado resumen de las ventas de un cliente");
				System.out.println("5.- Exportar clientes a XML");
				System.out.println("6.- Salir");
				System.out.print("Seleccione una opción: ");
				tabla = teclado.nextInt();
				do{
					if(tabla==1){
						System.out.println("DATOS DEL NUEVO PRODUCTO A INSERTAR");
						System.out.print("Introduzca la id del producto: ");
						id=teclado.nextInt();
						teclado.nextLine();
						
						String sql2="select * from productos where id=?";
						PreparedStatement sentencia2=conexion.prepareStatement(sql2);
						sentencia2.setInt(1,id);
						ResultSet resul2 = sentencia2.executeQuery();
						if(resul2.next()){
							System.out.println("Ya existe un producto con ese id. Repita el proceso.");
						}
						else{
							do {
								existe = false;
								String sql1="INSERT INTO productos VALUES(?,?,?,?,?)";
								PreparedStatement sentencia1=conexion.prepareStatement(sql1);
								
								System.out.print("Introduzca la descripción del producto: ");
								descripcion = teclado.nextLine();
								System.out.print("Introduzca el stock actual del producto: ");
								stockactual = teclado.nextInt();
								teclado.nextLine();
								System.out.print("Introduzca el stock mínimo del producto: ");
								stockminimo = teclado.nextInt();
								teclado.nextLine();
								System.out.print("Introduzca el pvp del producto: ");
								pvp = teclado.nextFloat();
														
								sentencia1.setInt(1,id);
								sentencia1.setString(2,descripcion);
								sentencia1.setInt(3,stockactual);
								sentencia1.setInt(4,stockminimo);
								sentencia1.setFloat(5,pvp);
								
								sentencia1.executeUpdate();
								
								Statement sentencia3 = conexion.createStatement();
								ResultSet resul=sentencia3.executeQuery("Select * from productos");
								while (resul.next()) {
									System.out.println("Id: "+resul.getInt(1)+" - Descripción: "+resul.getString(2)+" - Stock actual: "+resul.getInt(3)+" - Stock mínimo: "+resul.getInt(4)+" - Pvp: "+resul.getFloat(5));
								}
								System.out.print("Introduzca la id del producto: ");
								id=teclado.nextInt();
								teclado.nextLine();
							} while (id!=0);
						}
						
					}
					
					if(tabla==2){
						System.out.println("DATOS DEL NUEVO CLIENTE A INSERTAR");
						System.out.print("Introduzca la id del cliente: ");
						id=teclado.nextInt();
						teclado.nextLine();
						
						String sql2="select * from cliente where id=?";
						PreparedStatement sentencia2=conexion.prepareStatement(sql2);
						sentencia2.setInt(1,id);
						ResultSet resul2 = sentencia2.executeQuery();
						if(resul2.next()){
							System.out.println("Ya existe un cliente con ese id. Repita el proceso.");
						}
						else{
							do {
								existe = false;
								String sql1="INSERT INTO cliente VALUES(?,?,?,?,?,?)";
								PreparedStatement sentencia1=conexion.prepareStatement(sql1);
								
								System.out.print("Introduzca el nombre del cliente: ");
								nombre = teclado.nextLine();
								System.out.print("Introduzca la direccion del cliente: ");
								direccion = teclado.nextLine();
								System.out.print("Introduzca la poblacion del cliente: ");
								poblacion = teclado.nextLine();
								System.out.print("Introduzca el teléfono del cliente: ");
								telefono = teclado.nextLine();
								System.out.print("Introduzca el nif del cliente: ");
								nif = teclado.nextLine();
								
														
								sentencia1.setInt(1,id);
								sentencia1.setString(2,nombre);
								sentencia1.setString(3,direccion);
								sentencia1.setString(4,poblacion);
								sentencia1.setString(5,telefono);
								sentencia1.setString(6,nif);
								
								sentencia1.executeUpdate();
								
								Statement sentencia3 = conexion.createStatement();
								ResultSet resul=sentencia3.executeQuery("Select * from cliente");
								while (resul.next()) {
									System.out.println("Id: "+resul.getInt(1)+" - Nombre: "+resul.getString(2)+" - Direccion: "+resul.getString(3)+" - Poblacion: "+resul.getString(4)+" - Telefono: "+resul.getString(5)+" - Nif: "+resul.getString(6));
								}
								if (existe) {
									System.out.println("Ya existe un cliente con ese id. Repita el proceso.");
								}
								System.out.print("Introduzca la id del cliente: ");
								id=teclado.nextInt();
								teclado.nextLine();
							} while (id!=0);
						}
					}
					
					if(tabla==3){
						System.out.println("DATOS DE LA NUEVA VENTA A INSERTAR");
						System.out.print("Introduzca la id de la venta: ");
						id=teclado.nextInt();
						teclado.nextLine();
						ventanula=false;
						
						String sql5="select * from ventas where idventa=?";
						PreparedStatement sentencia5=conexion.prepareStatement(sql5);
						sentencia5.setInt(1,id);
						ResultSet resul5 = sentencia5.executeQuery();
						if(resul5.next()){
							System.out.println("Ya existe una venta con ese id. Repita el proceso.");
						}
						else{
							do {
								String sql1="INSERT INTO ventas VALUES(?,?,?,?,?)";
								PreparedStatement sentencia1=conexion.prepareStatement(sql1);
								
								System.out.print("Introduzca la fecha de hoy: ");
								fechaventa = teclado.nextLine();
								System.out.print("Introduzca el id del producto: ");
								idproducto = teclado.nextInt();
								teclado.nextLine();
								do{
									existe = false;
									String sql6="select * from productos where id=?";
									PreparedStatement sentencia6=conexion.prepareStatement(sql6);
									sentencia6.setInt(1,idproducto);
									ResultSet resul6 = sentencia6.executeQuery();
									if(resul6.next()){
										existe=true;
										stock = resul6.getInt(3);
									}
									else{
										System.out.print("No existe ningún producto con esa id. Introduzcalo de nuevo por favor: ");
										idproducto = teclado.nextInt();
										teclado.nextLine();
									}
								}while(!existe);
								System.out.print("Introduzca el id del cliente: ");
								idcliente = teclado.nextInt();
								teclado.nextLine();
								do{
									existe = false;
									String sql7="select * from cliente where id=?";
									PreparedStatement sentencia7=conexion.prepareStatement(sql7);
									sentencia7.setInt(1,idcliente);
									ResultSet resul7 = sentencia7.executeQuery();
									if(resul7.next()){
										existe=true;
									}
									else{
										System.out.print("No existe ningún cliente con esa id. Introduzcalo de nuevo por favor: ");
										idcliente = teclado.nextInt();
										teclado.nextLine();
									}
								}while(!existe);
								System.out.print("Introduzca la cantidad del producto: ");
								cantidad = teclado.nextInt();
								while(cantidad<=0){
									System.out.print("La cantidad ha de ser mayor que 0. Introduzcala de nuevo por favor: ");
									cantidad = teclado.nextInt();
								}
								
								if (cantidad>stock){
									System.out.println("No hay stock disponible del producto seleccionado. No se puede realizar la venta");
									ventanula = true;
								}
											
								if (!ventanula){
									
									sentencia1.setInt(1,id);
									sentencia1.setString(2,fechaventa);
									sentencia1.setInt(3,idcliente);
									sentencia1.setInt(4,idproducto);
									sentencia1.setInt(5,cantidad);
								
									sentencia1.executeUpdate();
									
									String sql8="UPDATE productos set stockactual=? where id=?";
									PreparedStatement sentencia8=conexion.prepareStatement(sql8);
									
									sentencia8.setInt(1,stock-cantidad);
									sentencia8.setInt(2,idproducto);
									sentencia8.executeUpdate();
									
									
								
									Statement sentencia3 = conexion.createStatement();
									ResultSet resul=sentencia3.executeQuery("Select * from ventas");
									while (resul.next()) {
										System.out.println("Id: "+resul.getInt(1)+" - Fecha: "+resul.getString(2)+" - Id producto: "+resul.getInt(3)+" - Id cliente: "+resul.getInt(4)+" - Cantidad: "+resul.getInt(5));
									}
									System.out.print("Introduzca la id de la venta: ");
									id=teclado.nextInt();
									teclado.nextLine();
								}
							} while (!ventanula && id!=0);
						}
						
					}
					if (tabla==4){
						hayventas=false;
						System.out.print("Introduzca la id del cliente: ");
						id=teclado.nextInt();
						teclado.nextLine();
						
						String sql2="select * from cliente where id=?";
						PreparedStatement sentencia2=conexion.prepareStatement(sql2);
						sentencia2.setInt(1,id);
						ResultSet resul2 = sentencia2.executeQuery();
						if(resul2.next()){
							System.out.println("DATOS DEL CLIENTE");
							System.out.println("Id: "+resul2.getInt(1)+" - Nombre: "+resul2.getString(2)+" - Direccion: "+resul2.getString(3)+" - Poblacion: "+resul2.getString(4)+" - Telefono: "+resul2.getString(5)+" - Nif: "+resul2.getString(6));
						}
						else{
							System.out.println("No existe un cliente con ese id");
						}
						
						String sql3="select * from ventas where idcliente=?";
						PreparedStatement sentencia3=conexion.prepareStatement(sql3);
						sentencia3.setInt(1,id);
						ResultSet resul3 = sentencia3.executeQuery();
						System.out.println("DATOS DE LAS VENTAS DEL CLIENTE");
						while(resul3.next()){
							hayventas=true;
							System.out.println("Idventa: "+resul3.getString(1)+" - Fecha de venta: "+resul3.getString(2)+" - IdCliente: "+resul3.getInt(3)+" - IdProducto: "+resul3.getInt(4)+" - Cantidad: "+resul3.getInt(5));
							idproducto=resul3.getInt(4);
							cantidad=resul3.getInt(5);
							String sql4="select pvp from productos where id=?";
							PreparedStatement sentencia4=conexion.prepareStatement(sql4);
							sentencia4.setInt(1,idproducto);
							ResultSet resul4 = sentencia4.executeQuery();
							if(resul4.next()){
								pvp=resul4.getFloat(1);
								total=total+pvp*cantidad;
							}
							numventas++;
							canttotal=canttotal+cantidad;
						}
						if(!hayventas){
							System.out.println("No existen ventas para este cliente");
						}
						System.out.println("El cliente con id="+id+" ha realizado "+numventas+ " compras con un total de "+canttotal+" productos por un valor total de "+total);
						System.out.println();
					}
					
					if(tabla==5){
						try {
							DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
							DocumentBuilder db = dbf.newDocumentBuilder();
							Document doc = db.newDocument();
							//Document doc=DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
							
							/*
							 * Tenemos un Document en memoria que representa el árbol del ficherl XML.
							 * Este árbol está vacío, el primer paso es asignar la versión de XML y el nodo raíz
							 */
							doc.setXmlVersion("1.0");
							Element raiz=doc.createElement("clientes");
							doc.appendChild(raiz);

							Statement sentenciaCli = conexion.createStatement();
							ResultSet resul=sentenciaCli.executeQuery("Select * from cliente");
						
							while(resul.next()){
								direccion=resul.getString(3);
								poblacion=resul.getString(4);
								telefono=resul.getString(5);
								nif=resul.getString(6);
								
								//crear el nodo y añadir un atributo
								Element nodo=doc.createElement("cliente");
								nodo.setAttribute("id", Integer.toString(resul.getInt(1)));
								
								// Crear el nodo del nombre con su contenido y
								// posteriormente lo añadimos
								Element nom = doc.createElement("nombre");
								Text textnom = doc.createTextNode(resul.getString(2));
								nom.appendChild(textnom);
								nodo.appendChild(nom);
								
								// Crear el nodo del nombre con su contenido y
								// posteriormente lo añadimos
								Element dir = doc.createElement("direccion");
								Text textdir = doc.createTextNode(resul.getString(3));
								dir.appendChild(textdir);
								nodo.appendChild(dir);
								
								// Crear el nodo del nombre con su contenido y
								// posteriormente lo añadimos
								Element pob = doc.createElement("poblacion");
								Text textpob = doc.createTextNode(resul.getString(4));
								pob.appendChild(textpob);
								nodo.appendChild(pob);
								
								// Crear el nodo del nombre con su contenido y
								// posteriormente lo añadimos
								Element tel = doc.createElement("telefono");
								Text texttel = doc.createTextNode(resul.getString(5));
								tel.appendChild(texttel);
								nodo.appendChild(tel);
								
								// Crear el nodo del nombre con su contenido y
								// posteriormente lo añadimos
								Element dni = doc.createElement("nif");
								Text textdni = doc.createTextNode(resul.getString(6));
								dni.appendChild(textdni);
								nodo.appendChild(dni);
								
								// añadir el nodo al Document
								doc.getDocumentElement().appendChild(nodo);
							}



							/*
							 * El Document no tiene formato y está en memoria. En necesario
							 * darle un formato y guardarlo en un fichero de texto, del tipo
							 * XML.
							 */
							Source source = new DOMSource(doc);
							Result result = new StreamResult(new File(
									"c:\\datos\\clientes.xml"));

							// Transformación del Document al fichero
							Transformer trans = TransformerFactory.newInstance()
									.newTransformer();
							trans.transform(source, result);

						} catch (ParserConfigurationException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
						
					
					
					
					
					System.out.println("MENU DE OPCIONES");
					System.out.println("1.- Insertar Producto");
					System.out.println("2.- Insertar Cliente");
					System.out.println("3.- Insertar Venta");
					System.out.println("4.- Listado resumen de las ventas de un cliente");
					System.out.println("5.- Exportar clientes a XML");
					System.out.println("6.- Salir");
					System.out.print("Seleccione una opción: ");
					tabla = teclado.nextInt();
				}while(tabla!=6);
				
				conexion.close();
			} catch (ClassNotFoundException cn) {
				cn.printStackTrace();
			} catch (InstantiationException ie) {
				ie.printStackTrace();
			} catch (IllegalAccessException ia) {
				ia.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			System.out.println("MENÚ DE INSERCIÓN EN BASE DE DATOS TIENDA");
			System.out.println("1.- MySQL");
			System.out.println("2.- SQLite");
			System.out.println("3.- Salir");
			System.out.print("Seleccione la base de datos: ");
			base = teclado.nextInt();
		} while (base != 3);
	}// fin del main

}
