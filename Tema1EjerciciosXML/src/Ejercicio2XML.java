import java.io.*;
import java.util.Scanner;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.*;

public class Ejercicio2XML {
	public static void main(String[] args) {
		Scanner teclado=new Scanner(System.in);
		String nom, ape;
		//Creación del parse para cargar el documento xml
		DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc = db.parse(new File("c:\\datos\\alumnos.xml"));
			doc.getDocumentElement().normalize();
			System.out.println("raiz: "+doc.getDocumentElement().getNodeName());
			NodeList alumnos =doc.getElementsByTagName("alumno");
			for (int i=0; i<alumnos.getLength(); i++){
				Node alum=alumnos.item(i);
				if (alum.getNodeType()==Node.ELEMENT_NODE){
					Element elemento=(Element) alum;
					System.out.println("Nombre: "+getNodo("nombre",elemento));
					System.out.println("Apellidos: "+getNodo("apellidos", elemento));
				}
			}
			
			System.out.print(("Nuevo nombre: "));
			nom=teclado.nextLine();
			System.out.print("Nuevos apellidos: ");
			ape=teclado.nextLine();
			//Crear un nuevo nodo con los elementos leídos
			Element nodo=doc.createElement("alumno");
			doc.getDocumentElement().appendChild(nodo);
			CrearElemento("nombre", nom,nodo,doc);
			CrearElemento("apellidos",ape,nodo,doc);
			//Crear el XML a partir del documento
			Source source=new DOMSource(doc);
			Result result=new StreamResult(new java.io.File("c:\\datos\\alumnos.xml"));
			Transformer transformer=TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);
			
		}catch (Exception e){e.printStackTrace();}
		teclado.close();
	}
	private static String getNodo(String etiqueta, Element elem) {
		NodeList nodo = elem.getElementsByTagName(etiqueta).item(0)
				.getChildNodes();
		Node valornodo = (Node) nodo.item(0);
		return valornodo.getNodeValue(); // devuelve valor del nodo
	}
	static void CrearElemento(String datoEmple, String valor, Element nodo,
			Document Alumnos) {
		Element elem = Alumnos.createElement(datoEmple); // creamos el hijo
		Text text = Alumnos.createTextNode(valor); // damos valor
		elem.appendChild(text); // pegamos el valor
		nodo.appendChild(elem); // pegamos el hijo a la raiz
	}
}
