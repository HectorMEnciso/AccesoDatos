import java.io.BufferedReader;
import java.io.File;
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

public class Ejercicio3XML_b {

	public static void main(String[] args) {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		Scanner teclado=new Scanner(System.in);
		String dni, nom, ape;
		float base, comple, irpf;
				
		try {
			File fic=new File("c:\\Datos\\Trabajadores.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc = null;
			if (fic.exists()){
				doc=db.parse(fic);
			}else{
				doc= db.newDocument();
				doc.setXmlVersion("1.0");
				Element raiz=doc.createElement("trabajadores");
				doc.appendChild(raiz);
			}
			//Construir el parser para el documento xml
				
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
				Element nodo = doc.createElement("trabajador");
				doc.getDocumentElement().appendChild(nodo);
				nodo.setAttribute("dni", dni);
				CrearElemento("nombre",nom,nodo,doc);
				CrearElemento("apellidos",ape,nodo,doc);
				//Crear el nodo sueldo, del que colgará los otros nodos
				Element sueldo=doc.createElement("sueldo");
				nodo.appendChild(sueldo); //Añadirlo al principal
				CrearElemento("base",Float.toString(base),sueldo,doc);
				CrearElemento("complementos",Float.toString(comple),sueldo,doc);
				CrearElemento("irpf",Float.toString(irpf),sueldo,doc);
				
				//Preguntar si se desea introducir más datos
				System.out.print("Desea continuar (S/N): ");
				cont=teclado.next().toUpperCase();
				
			} 
			// Crear la fuente XML a partir del documento
			Source source = new DOMSource(doc);
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
		elem.appendChild(text); // pegamos el valor
		nodo.appendChild(elem); // pegamos el hijo a la raiz
	}

} //fin de la clase
