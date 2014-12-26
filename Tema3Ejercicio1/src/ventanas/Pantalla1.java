package ventanas;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JLabel;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import clases.*;
import javax.swing.JTextArea;

public class Pantalla1 extends JFrame {
	int op=0,opProductos;
	static int idProducto;
	int stockActual;
	int stockMinimo;
	int pvp;
	int idCliente;
	int idVenta;
	int cantidad;
	String descripcion, nombre, direccion,poblacion,telefono, nif,fechaVenta;
	float precioTotal=0;
	private static final long serialVersionUID=1L;
	private JPanel contentPane;
	private static JTextField TidProducto;
	private static JTextField Tdescripcion;
	private static JTextField TstockActual;
	private static JTextField TstockMinimo;
	private static JTextField Tpvp;
	private static JLabel lbl1;
	private static JTextArea area;

	public Pantalla1() {
		setTitle("MANTENIMIENTO PRODUCTOS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnModificar = new JButton("Modificacion");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int idModificar=Integer.parseInt(TidProducto.getText());
				ModificarProducto(idModificar,lbl1);
			}
		});
		btnModificar.setBounds(223, 228, 114, 23);
		contentPane.add(btnModificar);
		
		JButton btnListados = new JButton("Listados");
		btnListados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListarProductos(area);
			}
		});
		btnListados.setBounds(347, 228, 89, 23);
		contentPane.add(btnListados);
		
		JButton btnAltas = new JButton("Altas");
		btnAltas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idAñadir=Integer.parseInt(TidProducto.getText());
				altaProducto(idAñadir,lbl1);
			}
		});
		btnAltas.setBounds(25, 228, 89, 23);
		contentPane.add(btnAltas);
		
		JButton btnBajas = new JButton("Bajas");
		btnBajas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int idBorrar=Integer.parseInt(TidProducto.getText());
				bajaProducto(idBorrar,lbl1);
			}
		});
		btnBajas.setBounds(124, 228, 89, 23);
		contentPane.add(btnBajas);
		
		TidProducto = new JTextField();
		TidProducto.setBounds(124, 11, 157, 20);
		contentPane.add(TidProducto);
		TidProducto.setColumns(10);
		
		JLabel lblIDproducto = new JLabel("idProducto");
		lblIDproducto.setBounds(10, 11, 78, 14);
		contentPane.add(lblIDproducto);
		
		 lbl1 = new JLabel("Label");
		 lbl1.setBounds(10, 181, 500, 14);
		contentPane.add(lbl1);
		
		JLabel lblDescripcion = new JLabel("descripcion");
		lblDescripcion.setBounds(10, 45, 78, 14);
		contentPane.add(lblDescripcion);
		
		JLabel lblStockactual = new JLabel("stockActual");
		lblStockactual.setBounds(10, 82, 78, 14);
		contentPane.add(lblStockactual);
		
		JLabel lblStockminimo = new JLabel("stockMinimo");
		lblStockminimo.setBounds(10, 107, 78, 14);
		contentPane.add(lblStockminimo);
		
		JLabel lblPvp = new JLabel("pvp");
		lblPvp.setBounds(10, 141, 78, 14);
		contentPane.add(lblPvp);
		
		Tdescripcion = new JTextField();
		Tdescripcion.setColumns(10);
		Tdescripcion.setBounds(124, 42, 157, 20);
		contentPane.add(Tdescripcion);
		
		TstockActual = new JTextField();
		TstockActual.setColumns(10);
		TstockActual.setBounds(124, 73, 157, 20);
		contentPane.add(TstockActual);
		
		TstockMinimo = new JTextField();
		TstockMinimo.setColumns(10);
		TstockMinimo.setBounds(124, 104, 157, 20);
		contentPane.add(TstockMinimo);
		
		Tpvp = new JTextField();
		Tpvp.setColumns(10);
		Tpvp.setBounds(124, 138, 157, 20);
		contentPane.add(Tpvp);
		
		JButton btnComprobarExistencia = new JButton("Comprobar existencia");
		btnComprobarExistencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comprobarExistencias(lbl1);
			}
		});
		btnComprobarExistencia.setBounds(291, 10, 174, 23);
		contentPane.add(btnComprobarExistencia);
		
		JButton btnVerProducto = new JButton("Ver producto");
		btnVerProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VerProducto(lbl1);
			}
		});
		btnVerProducto.setBounds(291, 41, 114, 23);
		contentPane.add(btnVerProducto);
		
		area = new JTextArea();
		area.setBounds(25, 275, 485, 173);
		contentPane.add(area);
	}
	public static void ListarProductos(JTextArea area){
		SessionFactory SF=SessionFactoryUtil.createSessionFactory();
		Session sesion=SF.openSession();//abrir la conexion
		//Productos productos=new Productos();
		String sql="select id,descripcion,stockactual,stockminimo,pvp from Productos";
		Query resul=sesion.createQuery(sql);
		List<Object[]> filas=resul.list();
	area.setText("");
		
		for (int i=0; i<filas.size(); i++){
			Object[] objResul=(Object[])filas.get(i);
			
		area.append(("ID: "+objResul[0]+" DESCRIPCION: "+ objResul[1]+ " STOCK ACTUAL: "+objResul[2]
					+" STOCK MINIMO:"+objResul[3]+" PVP: "+objResul[4]+"\n"));
		}
		
		sesion.close();
	}
	public static void VerProducto(JLabel lblError){
		SessionFactory SF=SessionFactoryUtil.createSessionFactory();
		Session sesion=SF.openSession();//abrir la conexion
		Productos productos=new Productos();
		productos = new Productos();
		productos=(Productos)sesion.get(Productos.class, Integer.parseInt(TidProducto.getText()));
		if(productos==null){
			lblError.setText("No puede mostrarse ya que el producto no existe.");
		}else {
			TidProducto.setText(Integer.toString(productos.getId()));
			Tdescripcion.setText((productos.getDescripcion()));
			TstockActual.setText(Integer.toString(productos.getStockactual()));
			TstockMinimo.setText(Integer.toString(productos.getStockminimo()));
			Tpvp.setText(Integer.toString(productos.getPvp()));
		}
		sesion.close();
	}
	public static void altaProducto(int idAlta,JLabel lblError){
		SessionFactory SF=SessionFactoryUtil.createSessionFactory();
		Session sesion=SF.openSession();//abrir la conexion
		Transaction tx=null;//marca el inicio de la transaccion;
		Productos productos=null;
		productos=(Productos)sesion.get(Productos.class, idAlta);
		if(productos!=null){
			lblError.setText("No puede darse de alta ya que ese producto ya existe.");
		}
		else {
			tx=sesion.beginTransaction();//marca el inicio de la transaccion;
			productos = new Productos();
			productos.setId(Integer.parseInt(TidProducto.getText()));
			productos.setDescripcion(Tdescripcion.getText());
			productos.setStockactual(Integer.parseInt(TstockActual.getText()));
			productos.setStockminimo(Integer.parseInt(TstockMinimo.getText()));
			productos.setPvp(Integer.parseInt(Tpvp.getText()));
			sesion.save(productos);//salvar los cambios//instancia persistente
			tx.commit();//validar la transaccion
			lblError.setText("El producto ha sido dado de alta correctamente.");
		}
		sesion.close();
	}
	public static void bajaProducto(int idBorrar,JLabel lblError){
		SessionFactory SF=SessionFactoryUtil.createSessionFactory();
		Session sesion=SF.openSession();//abrir la conexion
		Transaction tx=null;
		Productos productos=new Productos();
		productos = new Productos();
		productos=(Productos)sesion.get(Productos.class, idBorrar);
		if(productos==null){
			lblError.setText("No puede borrarse ya que el producto no existe.");
		}
		else {
			tx=sesion.beginTransaction();//marca el inicio de la transaccion;
			sesion.delete(productos);//salvar los cambios//instancia persistente
			tx.commit();//validar la transaccion
			lblError.setText("El producto ha sido borrado correctamente.");
		}
		sesion.close();
	}
	public static void ModificarProducto(int idModificar,JLabel lblError){
		SessionFactory SF=SessionFactoryUtil.createSessionFactory();
		Session sesion=SF.openSession();//abrir la conexion
		Transaction tx=null;
		Productos productos=new Productos();
		productos = new Productos();
		productos=(Productos)sesion.get(Productos.class, idModificar);
		if(productos==null){
			lblError.setText("No puede modificarse ya que el producto no existe.");
		}
		else {
			tx=sesion.beginTransaction();//marca el inicio de la transaccion;
			productos.setId(Integer.parseInt(TidProducto.getText()));
			productos.setDescripcion(Tdescripcion.getText());
			productos.setStockactual(Integer.parseInt(TstockActual.getText()));
			productos.setStockminimo(Integer.parseInt(TstockMinimo.getText()));
			productos.setPvp(Integer.parseInt(Tpvp.getText()));
			sesion.update(productos);//salvar los cambios//instancia persistente
			tx.commit();//validar la transaccion
			lblError.setText("El producto ha sido modificado correctamente.");
		}
		sesion.close();
	}
	public static void comprobarExistencias(JLabel lblError){
		SessionFactory SF=SessionFactoryUtil.createSessionFactory();
		Session sesion=SF.openSession();
		if (Integer.parseInt(TidProducto.getText())==0) {
			lblError.setText("El idproducto no puede ser 0.");
		}
		else{
			Query resul = sesion.createQuery("from Productos where id = :idProduc");
			resul.setInteger("idProduc", Integer.parseInt(TidProducto.getText())); 
			Iterator iter = resul.iterate();
			if(iter.hasNext()){
				lblError.setText("Ya existe un producto con ese ID");
			}
		}
		sesion.close();
	}
}
