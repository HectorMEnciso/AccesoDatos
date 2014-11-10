/*
 * Volcar los datos de un documento xml en un fichero aleatorio
 */
import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
public class Ejercicio4XML {

	public static void main(String[] args) throws IOException {
		File fic=new File("c:\\datos\\Trabajadores.dat");
		RandomAccessFile ficale=new RandomAccessFile(fic,"rw");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		int tamano=2*(9+15+30)+3*4; // tamaño del registro a guardar
		int posicion=0;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new File("c:\\datos\\Trabajadores.xml"));
			doc.getDocumentElement().normalize();
			// Obtener la lista de nodos con nombre empleado de todo el documento
			NodeList lista = doc.getElementsByTagName("trabajador");
			/*
			 * Recorrer la lista de nodos obtenida. Por cada nodo se obtienen
			 * sus etiquetas y sus valores con la función getNodo()
			 */
			for (int i=0;i<lista.getLength();i++){
				Node trab = lista.item(i);
				if (trab.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element)trab;
					String dni = elemento.getAttribute("dni");
					String nom=getNodo("nombre", elemento);
					String ape=getNodo("apellidos", elemento);
					float base=getNodoFloat("base", elemento);
					float comp=getNodoFloat("complementos", elemento);
					float irpf=getNodoFloat("irpf", elemento);
					/*System.out.println("dni: "+dni);
					System.out.println("nombre: "+nom);
					System.out.println("apellidos: "+ape);
					System.out.println("base: "+base);
					System.out.println("complementos: "+comp);
					System.out.println("irpf: "+irpf);*/
					
					ficale.seek(posicion);
					write(ficale,dni,9);
					write(ficale,nom,15);
					write(ficale,ape,30);
					ficale.writeFloat(base);
					ficale.writeFloat(comp);
					ficale.writeFloat(irpf);
										
				}
				posicion=posicion+tamano;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		ficale.close();
				
	} // fin del main
	// Obtener la información del nodo
	/**
	 * Obtener el contenido de un elemento del documento XML
	 * @param etiqueta Nombre del elemento del cual deseamos obtener la información
	 * @param elem Elemento que estamos analizando
	 * @return Devuelve el contenido del nodo
	 */
	private static String getNodo(String etiqueta, Element elem) {
		NodeList nodo = elem.getElementsByTagName(etiqueta).item(0)
				.getChildNodes();
		Node valornodo = (Node) nodo.item(0);
		return valornodo.getNodeValue(); // devuelve valor del nodo
	}
	private static float getNodoFloat(String etiqueta, Element elem) {
		NodeList nodo = elem.getElementsByTagName(etiqueta).item(0)
				.getChildNodes();
		Node valornodo = (Node) nodo.item(0);
		return Float.parseFloat(valornodo.getNodeValue()); // devuelve valor del nodo
	}
	private static void write(RandomAccessFile raf, String campo, int lon) throws IOException{
		StringBuffer sb;
		sb=new StringBuffer(campo);
		sb.setLength(lon);
		raf.writeChars(sb.toString());
		
	}
	

}// fin de la class
