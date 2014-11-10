import java.io.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;

public class ejer4 {
	public static void main(String arg[]) throws IOException {
		File fic = new File("c:\\datos\\empleados2.dat");
		String dni, nombre, apellidos;
		float base, comp, irpf;
		Empleados emp = new Empleados();
		RandomAccessFile raf = new RandomAccessFile(fic, "rw");
		// RandomAccessFile rafEscribir = new RandomAccessFile(fic, "w");
		// Departamento departamento;

		InputStreamReader entrada = new InputStreamReader(System.in);
		BufferedReader teclado = new BufferedReader(entrada);
		try {

			// Fabricar el documento
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse("c:\\datos\\empleados2.xml");
			//
			// Obtener el nodo raíz del fichero
			Element raiz = doc.getDocumentElement();
			//
			// Obtener la lista de nodos que tienen la etiqueta "cliente"
			NodeList listaEmpleados = raiz.getElementsByTagName("empleado");
			NodeList listaSueldo = raiz.getElementsByTagName("sueldo");// hacer
																		// lista
																		// para
																		// cada
																		// hijo
			//
			// Recorrer la lista de los nodos encontrados
			for (int i = 0; i < listaEmpleados.getLength(); i++) {
				//
				
				Node empleado = listaEmpleados.item(i);// Obtener de la lista un empleado tras otro
				Node sueldo = listaSueldo.item(i);// recorremos los items dentro de sueldo
				System.out.println("\nLeyendo del fichero XML...");
				
				//
				// Obtener el valor del atributo nif
				//
				Node atr = empleado.getAttributes().getNamedItem("dni");
				System.out.println(atr.getNodeName() + ": "+ atr.getNodeValue());
				emp.setDni(atr.getNodeValue());
				//
				// Obtener los elementos que compone el nodo cliente
				//
				NodeList datosEmpleados = empleado.getChildNodes();

				//
				// Recorrer los elementos del nodo clientef
				for (int j = 0; j < datosEmpleados.getLength(); j++) {
					// Obtener de la lista un elemento tras otro
					Node nodoEmpleado = datosEmpleados.item(j);
					// Comprobar que el elemento se trata de uno del tipo
					// Elemento
					if (nodoEmpleado.getNodeType() == Node.ELEMENT_NODE) {
						// Mostrar del nombre del tipo del elemento
						// System.out.println(nodoCliente.getNodeName()+": ");
						// Obtener el valor contenido en el elemento
						Node datoElemento = nodoEmpleado.getFirstChild();
						// Mostrar el contenido del atributo
						if (datoElemento != null&& datoElemento.getNodeType() == Node.TEXT_NODE) {
							System.out.println(nodoEmpleado.getNodeName()+ ": " + datoElemento.getNodeValue());
							if (j == 0) {
								emp.setNombre(datoElemento.getNodeValue());
							} else if (j == 1) {
								emp.setApellido(datoElemento.getNodeValue());
							}
							/*
							 * if (nodoEmpleado.getNodeName()=="nombre"){
							 * emp.setNombre(datoElemento.getNodeValue()); }
							 * else if(nodoEmpleado.getNodeName()=="apellidos"){
							 * emp.setApellido(datoElemento.getNodeValue()); }
							 */
						}
					}

				}
				NodeList datosSueldo = sueldo.getChildNodes();// listamos los
																// nodos hijos
				for (int j = 0; j < datosSueldo.getLength(); j++) {
					// Obtener de la lista un elemento tras otro
					Node nodoSueldo = datosSueldo.item(j);
					// Comprobar que el elemento se trata de uno del tipo
					// Elemento
					if (nodoSueldo.getNodeType() == Node.ELEMENT_NODE) {
						// Mostrar del nombre del tipo del elemento
						// System.out.println(nodoCliente.getNodeName()+": ");
						// Obtener el valor contenido en el elemento
						Node datoElemento = nodoSueldo.getFirstChild();
						// Mostrar el contenido del atributo
						if (datoElemento != null&& datoElemento.getNodeType() == Node.TEXT_NODE) {
							System.out.println(nodoSueldo.getNodeName() + ": "+ datoElemento.getNodeValue());
							if (nodoSueldo.getNodeName() == "base") {
								emp.setBase(Float.parseFloat(datoElemento.getNodeValue()));
							} else if (nodoSueldo.getNodeName() == "complementos") {
								emp.setComplemento(Float.parseFloat(datoElemento.getNodeValue()));
							} else if (nodoSueldo.getNodeName() == "irpf") {
								emp.setIRPF(Float.parseFloat(datoElemento.getNodeValue()));
							}

						}
					}

				}
				emp.write(raf);
				System.out.println("dni: " + emp.getDni());
			}

		} catch (IOException ex) {
			System.out.println("ERROR: no se ha podido leer el fichero "
					+ ex.getMessage());
			ex.printStackTrace();
		} catch (ParserConfigurationException ex) {
			System.out
					.println("ERROR: No se ha podido crear el generador de documentos XML "
							+ ex.getMessage());
			ex.printStackTrace();
		} catch (SAXException ex) {
			System.out
					.println("ERROR: el formato XML del fichero no es correcto "
							+ ex.getMessage());
			ex.printStackTrace();
		}
		System.out.println("Iniciando lectura fichero Java...");
		int numeroRegistros = (int) (fic.length() / emp.tamano());

		raf.seek(0);
		for (int x = 0; x < numeroRegistros; x++) {

			emp.leer(raf);
			System.out.println("Dni: " + emp.getDni());
			System.out.println("Nombre : " + emp.getNombre());
			System.out.println("Apellidos: " + emp.getApellido());
			System.out.println("Base: " + emp.getBase());
			System.out.println("Complementos: " + emp.getComp());
			System.out.println("IRPF: " + emp.getIrpf());
			System.out.println();
		}
	}
}
