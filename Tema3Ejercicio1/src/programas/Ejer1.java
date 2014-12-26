package programas;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.persistence.IdClass;

import clases.*;

import org.hibernate.*;
public class Ejer1 {
	public static void main(String[] args) {
		int op=0,opProductos,idProducto,stockActual,stockMinimo,pvp,idCliente,idVenta,cantidad;
		String descripcion, nombre, direccion,poblacion,telefono, nif,fechaVenta;
		float precioTotal=0;
		Scanner teclado=new Scanner(System.in);
		SessionFactory SF=SessionFactoryUtil.createSessionFactory();
		Session sesion=null;
		Transaction tx=null;
		Productos productos=new Productos();
		Clientes clientes=new Clientes();
		Ventas ventas= new Ventas();
		op=menu(teclado);
		do{
			switch (op){
			case 1:
				opProductos=menuProductos(teclado);
				do{
					switch (opProductos){
					case 1:
						sesion=SF.openSession();//abrir la conexion
						System.out.print("Introduzca ID del producto: ");
						idProducto=teclado.nextInt();
						teclado.nextLine();
						while(idProducto!=0){
							Query resul = sesion.createQuery("from Productos where id = :idProduc");
							resul.setInteger("idProduc", idProducto); 
							Iterator iter = resul.iterate();
							if(iter.hasNext()){
								System.out.println("Ya existe un producto con ese ID.");
							}
							else{
								tx=sesion.beginTransaction();//marca el inicio de la transaccion
								productos = new Productos();
								productos.setId(idProducto);
								System.out.print("Introduzca descripcion del producto: ");
								descripcion=teclado.nextLine();
								productos.setDescripcion(descripcion);
								System.out.print("Introduzca stock actual del producto: ");
								stockActual=teclado.nextInt();
								productos.setStockactual(stockActual);
								System.out.print("Introduzca stock minimo del producto: ");
								stockMinimo=teclado.nextInt();
								productos.setStockminimo(stockMinimo);
								System.out.print("Introduzca pvp del producto: ");
								pvp=teclado.nextInt();
								productos.setPvp(pvp);
								sesion.save(productos);//salvar los cambios//instancia persistente
								tx.commit();//validar la transaccion
							
							}
							System.out.print("Introduzca ID del producto (0 para finalizar): ");
							idProducto=teclado.nextInt();
							teclado.nextLine();
						}
						sesion.close();
					break;
					case 2:
						sesion=SF.openSession();//abrir la conexion
						System.out.print("Introduzca ID del producto a borrar: ");
						idProducto=teclado.nextInt();
						productos=(Productos)sesion.get(Productos.class, idProducto);
						if(productos==null){
							System.out.println("El producto no puede borrarse ya que no existe");
						}
						else {
							tx=sesion.beginTransaction();//marca el inicio de la transaccion
							sesion.delete(productos);
							tx.commit();
							System.out.println("El producto ha sido borrado.");
							System.out.println("\t ID: "+productos.getId()+" Descrición: "+productos.getDescripcion()+" Stock Actual: "
									+productos.getStockactual()+" StockMinimo: "+productos.getStockminimo()+" Precio: "+productos.getPvp());
						}
						sesion.close();
					break;
					case 3:
						sesion=SF.openSession();//abrir la conexion
						System.out.print("Introduzca ID del producto a modificar: ");
						idProducto=teclado.nextInt();
						teclado.nextLine();
						productos=(Productos)sesion.get(Productos.class, idProducto);
						if(productos==null){
							System.out.println("El producto no puede modificarse ya que no existe");
						}
						else {
							tx=sesion.beginTransaction();//marca el inicio de la transaccion
							System.out.println("ANTES.......");
							productos=(Productos)sesion.load(Productos.class, idProducto);
							System.out.println("\t ID: "+productos.getId()+" Descrición: "+productos.getDescripcion()+" Stock Actual: "
									+productos.getStockactual()+" StockMinimo: "+productos.getStockminimo()+" Precio: "+productos.getPvp());
							System.out.print("Introduzca descripcion del producto: ");
							descripcion=teclado.nextLine();
							productos.setDescripcion(descripcion);
							System.out.print("Introduzca stock actual del producto: ");
							stockActual=teclado.nextInt();
							productos.setStockactual(stockActual);
							System.out.print("Introduzca stock minimo del producto: ");
							stockMinimo=teclado.nextInt();
							productos.setStockminimo(stockMinimo);
							System.out.print("Introduzca pvp del producto: ");
							pvp=teclado.nextInt();
							productos.setPvp(pvp);
							sesion.update(productos);
							tx.commit();
							System.out.println("El producto ha sido modifcado.");
							System.out.println("Después.......");
							System.out.println("\t ID: "+productos.getId()+" Descrición: "+productos.getDescripcion()+" Stock Actual: "
									+productos.getStockactual()+" StockMinimo: "+productos.getStockminimo()+" Precio: "+productos.getPvp());
						}
						sesion.close();
					break;
					case 4:
						sesion=SF.openSession();//abrir la conexion
						//tx=sesion.beginTransaction();//marca el inicio de la transaccion
						System.out.println("LISTADO PRODUCTOS");
						String sql="select id,descripcion,stockactual,stockminimo,pvp from Productos";
						Query resul=sesion.createQuery(sql);
						List<Object[]> filas=resul.list();
						for (int i=0; i<filas.size(); i++){
							Object[] objResul=(Object[])filas.get(i);
							System.out.println("ID: "+objResul[0]+" DESCRIPCION: "+ objResul[1]+ " STOCK ACTUAL: "+objResul[2]
									+" STOCK MINIMO:"+objResul[3]+" PVP: "+objResul[4]);
						}
						sesion.close();
					break;
					
					case 5:
						System.out.println("Ha pulsado salir.");
					break;
					}
					
					opProductos=menuProductos(teclado);
				}while(opProductos!=5);
				
			break;
			case 2:
				System.out.println("ALTA TABLA CLIENTES");
				sesion=SF.openSession();//abrir la conexion
				System.out.print("Introduzca ID del cliente: ");
				idCliente=teclado.nextInt();
				teclado.nextLine();
				while(idCliente!=0){
					Query resul = sesion.createQuery("from Clientes where id = :idCliente");
					resul.setInteger("idCliente", idCliente); 
					Iterator iter = resul.iterate();
					if(iter.hasNext()){
						System.out.println("Ya existe un cliente con ese ID.");
					}
					else{
						tx=sesion.beginTransaction();//marca el inicio de la transaccion
						clientes=new Clientes();
						clientes.setId(idCliente);
						System.out.print("Introduzca nombre del cliente: ");
						nombre=teclado.nextLine();
						clientes.setNombre(nombre);
						System.out.print("Introduzca dirección del cliente: ");
						direccion=teclado.nextLine();
						clientes.setDireccion(direccion);
						System.out.print("Introduzca población del cliente: ");
						poblacion=teclado.nextLine();
						clientes.setPoblacion(poblacion);
						System.out.print("Introduzca telefono del cliente ");
						telefono=teclado.nextLine();
						clientes.setTelef(telefono);
						System.out.print("Introduzca NIF del cliente ");
						nif=teclado.nextLine();
						clientes.setNif(nif);
						sesion.save(clientes);//salvar los cambios//instancia persistente
						tx.commit();//validar la transaccion
						
						while (iter.hasNext()) {
							clientes = (Clientes) iter.next();
							System.out.println("\t ID: "+clientes.getId()+"  Nombre: "+clientes.getNombre()
									+ " Dirección: "+clientes.getDireccion()+" Población: "+clientes.getPoblacion()+" Teléfono: "+clientes.getTelef()+ " NIF: "+clientes.getNif());
						}
					}
					System.out.print("Introduzca ID del cliente (0 para finalizar): ");
					idCliente=teclado.nextInt();
					teclado.nextLine();
				}
				sesion.close();
			break;
			case 3:
				System.out.println("ALTA TABLA VENTAS");
				sesion=SF.openSession();//abrir la conexion
				System.out.print("Introduzca ID de la venta: ");
				idVenta=teclado.nextInt();
				teclado.nextLine();
				while(idVenta!=0){
					Query resul = null;
					ventas=(Ventas)sesion.get(Ventas.class, idVenta);
					while(ventas != null){
						System.out.print("Introduzca ID de la venta: ");
						idVenta=teclado.nextInt();
						teclado.nextLine();
						ventas=(Ventas)sesion.get(Ventas.class, idVenta);
					}
						tx=sesion.beginTransaction();//marca el inicio de la transaccion
						ventas = new Ventas();
						ventas.setIdventa(idVenta);
						
						System.out.print("Introduzca idCliente: ");
						idCliente=teclado.nextInt();
						clientes=(Clientes)sesion.get(Clientes.class, idCliente);
						while (clientes==null){
							System.out.print("Introduzca idCliente: ");
							idCliente=teclado.nextInt();
							clientes=(Clientes)sesion.get(Clientes.class, idCliente);
						}
						ventas.setClientes(clientes);
						
						System.out.print("Introduzca idProducto: ");
						idProducto=teclado.nextInt();
						teclado.nextLine();
						productos=(Productos)sesion.get(Productos.class, idProducto);
						while (productos==null){
							System.out.print("Introduzca productos: ");
							idProducto=teclado.nextInt();
							teclado.nextLine();
							productos=(Productos)sesion.get(Productos.class, idProducto);
						}
						ventas.setProductos(productos);
						
						System.out.print("Introduzca fecha de la venta: ");
						fechaVenta=teclado.nextLine();
						ventas.setFechaventa(fechaVenta);
	
						System.out.print("Introduzca cantidad (debe ser mayor que 0): ");
						cantidad=teclado.nextInt();
						while(cantidad<1){
							System.out.print("Introduzca cantidad (debe ser mayor que 0): ");
							cantidad=teclado.nextInt();
						}
						Query resulStock = sesion.createQuery("from Productos where id = :idProducto");
						resulStock.setInteger("idProducto", idProducto); 
						
						if(cantidad<productos.getStockactual()){
							ventas.setCantidad(cantidad);
							productos = (Productos) sesion.load(Productos.class, idProducto);
							productos.setStockactual(productos.getStockactual()-cantidad);
							sesion.update(productos);
							sesion.save(ventas);//salvar los cambios//instancia persistente
							tx.commit();//validar la transaccion
						}
					
					System.out.print("Introduzca ID de la venta (0 para finalizar): ");
					idVenta=teclado.nextInt();
					teclado.nextLine();
				}
				sesion.close();
				break;
			case 4:
				System.out.println("LISTADO VENTAS DE CLIENTES");
				sesion=SF.openSession();//abrir la conexion
				System.out.print("Introduzca ID del cliente: ");
				idCliente=teclado.nextInt();
				teclado.nextLine();
				while(idCliente!=0){
					clientes=(Clientes)sesion.get(Clientes.class, idCliente);
					if(clientes==null){
						System.out.println("No existe el cliente.");
					}
					else{
						Query resulListado = sesion.createQuery("from Productos as pro,Ventas as ven,Clientes as cli  where pro.id=ven.productos.id and cli.id=ven.clientes.id and ven.clientes.id=:idCliente");
						resulListado.setInteger("idCliente", idCliente); 
						Iterator iterListado=resulListado.iterate();
						while(iterListado.hasNext()){
							/*Se utiliza array de Object */
							Object[] objResul=(Object[])iterListado.next();
							clientes=(Clientes)objResul[2];//debe de coincidir con el orden de la consulta
							productos=(Productos)objResul[0];
							ventas=(Ventas)objResul[1];
						precioTotal=precioTotal+(productos.getPvp()*ventas.getCantidad());
							System.out.println("Id venta: "+ventas.getIdventa()+" Nombre cliente : "+ clientes.getNombre()+ 
									" Dirección: "+clientes.getDireccion()+ " Descripcion del producto: " +productos.getDescripcion()
									+" precio: " +productos.getPvp()+" cantidad: "+ventas.getCantidad());
							
						}
						System.out.println("Precio total de la venta: "+ precioTotal);
					}
					System.out.print("Listar otro cliente o 0 para finalizar: ");
					idCliente=teclado.nextInt();
					teclado.nextLine();	
					precioTotal=0;
				}
				sesion.close();
			break;
			}
			op = menu(teclado);
		} while (op != 5);
	}
	
	private static int menu(Scanner teclado) {
		int op;
		System.out.println("1.-Mantenimiento de la tabla de PRODUCTOS");
		System.out.println("2.-Altas en la tabla de CLIENTES");
		System.out.println("3.-Altas en la tabla de VENTAS ");
		System.out.println("4.- Listado VENTAS CLIENTES");
		System.out.println("5.- Salir");
		System.out.print("Elegir una opcion:");
		op = teclado.nextInt();
		return op;
	}
	
	private static int menuProductos(Scanner teclado) {
		int opProductos;
		System.out.println("Mantenimiento de la tabla de PRODUCTOS");
		System.out.println("1.- Altas");
		System.out.println("2.- Bajas");
		System.out.println("3.- Modificaciones");
		System.out.println("4.- Listado");
		System.out.println("5.- Salir");
		System.out.print("Elegir una opcion:");
		opProductos = teclado.nextInt();
		return opProductos;
	}
}
