import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Ejercicio3XML {

	public static void main(String[] args) {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		Scanner teclado=new Scanner(System.in);
		String dni, nom, ape;
		float base, comple, irpf;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			//Construir el parser para el documento xml
			DocumentBuilder db = dbf.newDocumentBuilder();
			DOMImplementation implementation = db.getDOMImplementation();
			Document Trabajadores;
			Trabajadores = implementation.createDocument(null, "nominas", null);
			Trabajadores.setXmlVersion("1.0");
			
			String cont = "S";
			while (cont.compareTo("S")==0){
				//Leer los datos que vamos a guardar
				System.out.println("Datos del trabajador");
				System.out.print("DNI: ");
				dni = br.readLine();
				System.out.print("NOMBRE: ");
				nom = br.readLine();
				System.out.print("APELLIDOS: ");
				ape = br.readLine();
				System.out.print("SUELDO BASE: ");
				base = teclado.nextFloat();
				System.out.print("SUELDO COMPLEMENTOS: ");
				comple = teclado.nextFloat();
				System.out.print("SUELDO IRPF: ");
				irpf = teclado.nextFloat();
				
				// Crear el elemento donde guardamos los datos
				Element nodo = Trabajadores.createElement("trabajador");
				Trabajadores.getDocumentElement().appendChild(nodo);
				nodo.setAttribute("dni", dni);
				CrearElemento("nombre",nom,nodo,Trabajadores);
				CrearElemento("apellidos",ape,nodo,Trabajadores);
				//Crear el nodo sueldo, del que colgará los otros nodos
				Element sueldo=Trabajadores.createElement("sueldo");
				nodo.appendChild(sueldo); //Añadirlo al principal
				CrearElemento("base",Float.toString(base),sueldo,Trabajadores);
				CrearElemento("complementos",Float.toString(comple),sueldo,Trabajadores);
				CrearElemento("irpf",Float.toString(irpf),sueldo,Trabajadores);
				
				//Preguntar si se desea introducir más datos
				System.out.print("Desea continuar (S/N): ");
				cont=teclado.next().toUpperCase();
				
			} 
			// Crear la fuente XML a partir del documento
			Source source = new DOMSource(Trabajadores);
			// Crear el resultado en el fichero Trabajadores.xml
			Result result = new StreamResult(new java.io.File(
					"c:\\Datos\\Trabajadores.xml"));
			// Transformar el documento a fichero
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		teclado.close();
	} // fin del main

	static void CrearElemento(String datoEmple, String valor, Element nodo,
			Document doc) {
		Element elem = doc.createElement(datoEmple); // creamos el hijo
		Text text = doc.createTextNode(valor); // damos valor
		nodo.appendChild(elem); // pegamos el hijo a la raiz
		elem.appendChild(text); // pegamos el valor
	}

} //fin de la clase
