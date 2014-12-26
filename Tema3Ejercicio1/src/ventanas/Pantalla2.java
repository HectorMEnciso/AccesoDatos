package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clases.Clientes;
import clases.Productos;
import clases.SessionFactoryUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;

public class Pantalla2 extends JFrame {

	private JPanel contentPane;
	private JTextField TidCliente;
	private JTextField Tnombre;
	private JTextField Tdireccion;
	private JTextField Tpoblacion;
	private JTextField Ttelefono;
	private JTextField Tnif;
	private JLabel lblInfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pantalla2 frame = new Pantalla2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Pantalla2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 438, 271);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnEjecutarConsulta = new JButton("Ejecutar Consulta");
		btnEjecutarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SessionFactory SF=SessionFactoryUtil.createSessionFactory();
				Session sesion=SF.openSession();//abrir la conexion
				Clientes clientes=new Clientes();
				Query resul=sesion.createQuery("select id from Clientes where id=(select min(id) from Clientes)");
				List <Clientes> lista=resul.list();
				Iterator <Clientes> iter = lista.iterator();
				
				while (iter.hasNext()){
					//recorrer la lista y extraer la información
					clientes =iter.next();
					TidCliente.setText(Integer.toString(clientes.getId()));
					Tnombre.setText(clientes.getNombre());
					Tdireccion.setText(clientes.getDireccion());
					Tpoblacion.setText(clientes.getPoblacion());
					Ttelefono.setText(clientes.getTelef());
					Tnif.setText(clientes.getNif());
				}
				sesion.close();
			}
		});
		btnEjecutarConsulta.setBounds(207, 7, 146, 23);
		contentPane.add(btnEjecutarConsulta);
		
		JButton btnPrimerRegistro = new JButton("Primer Registro");
		btnPrimerRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SessionFactory SF=SessionFactoryUtil.createSessionFactory();
				Session sesion=SF.openSession();//abrir la conexion
				Clientes clientes=new Clientes();
				clientes = new Clientes();
				clientes=(Clientes)sesion.get(Clientes.class, 1);
				if(clientes==null){
					lblInfo.setText("No hay clientes.");
				}else {
					TidCliente.setText(Integer.toString(clientes.getId()));
					Tnombre.setText(clientes.getNombre());
					Tdireccion.setText(clientes.getDireccion());
					Tpoblacion.setText(clientes.getPoblacion());
					Ttelefono.setText(clientes.getTelef());
					Tnif.setText(clientes.getNif());
				}
				sesion.close();
			}
		});
		btnPrimerRegistro.setBounds(207, 41, 146, 23);
		contentPane.add(btnPrimerRegistro);
		
		JButton btnltimoRegistro = new JButton("\u00DAltimo Registro");
		btnltimoRegistro.setBounds(207, 75, 146, 23);
		contentPane.add(btnltimoRegistro);
		
		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setBounds(207, 109, 146, 23);
		contentPane.add(btnSiguiente);
		
		JButton btnAnterior = new JButton("Anterior");
		btnAnterior.setBounds(207, 143, 146, 23);
		contentPane.add(btnAnterior);
		
		JLabel lblIdCliente = new JLabel("ID CLIENTE:");
		lblIdCliente.setBounds(10, 11, 72, 14);
		contentPane.add(lblIdCliente);
		
		JLabel lblNombre = new JLabel("NOMBRE:");
		lblNombre.setBounds(10, 36, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblDireccion = new JLabel("DIRECCI\u00D3N:");
		lblDireccion.setBounds(10, 61, 72, 14);
		contentPane.add(lblDireccion);
		
		JLabel lblPoblacin = new JLabel("POBLACI\u00D3N:");
		lblPoblacin.setBounds(10, 86, 72, 14);
		contentPane.add(lblPoblacin);
		
		JLabel lblTelfono = new JLabel("TEL\u00C9FONO:");
		lblTelfono.setBounds(10, 111, 72, 14);
		contentPane.add(lblTelfono);
		
		JLabel lblNif = new JLabel("NIF:");
		lblNif.setBounds(10, 136, 46, 14);
		contentPane.add(lblNif);
		
		TidCliente = new JTextField();
		TidCliente.setBounds(92, 8, 86, 20);
		contentPane.add(TidCliente);
		TidCliente.setColumns(10);
		
		Tnombre = new JTextField();
		Tnombre.setBounds(92, 33, 86, 20);
		contentPane.add(Tnombre);
		Tnombre.setColumns(10);
		
		Tdireccion = new JTextField();
		Tdireccion.setBounds(92, 58, 86, 20);
		contentPane.add(Tdireccion);
		Tdireccion.setColumns(10);
		
		Tpoblacion = new JTextField();
		Tpoblacion.setBounds(92, 83, 86, 20);
		contentPane.add(Tpoblacion);
		Tpoblacion.setColumns(10);
		
		Ttelefono = new JTextField();
		Ttelefono.setBounds(92, 108, 86, 20);
		contentPane.add(Ttelefono);
		Ttelefono.setColumns(10);
		
		Tnif = new JTextField();
		Tnif.setBounds(92, 133, 86, 20);
		contentPane.add(Tnif);
		Tnif.setColumns(10);
		
		lblInfo = new JLabel("New label");
		lblInfo.setBounds(10, 189, 332, 14);
		contentPane.add(lblInfo);
	}
}
