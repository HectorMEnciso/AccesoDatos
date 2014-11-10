import java.io.*;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
public class ejer3 {
	public static void main(String arg[]) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader teclado = new BufferedReader(isr);
		String nombre, apellido, dni;
		float base, complementos, irpf;
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
			Element raiz=doc.createElement("empleados");
			doc.appendChild(raiz);
			
			System.out.println("Introduce dni: ");
			dni=teclado.readLine();
			System.out.println("Introduce nombre: ");
			nombre=teclado.readLine();
			System.out.println("Introduce apellido: ");
			apellido=teclado.readLine();
			System.out.println("Introduce sueldo base: ");
			base=Float.parseFloat(teclado.readLine());
			System.out.println("Introduce complementos: ");
			complementos=Float.parseFloat(teclado.readLine());
			System.out.println("Introduce irpf: ");
			irpf=Float.parseFloat(teclado.readLine());
			//Procedemos a rellenar cada uno de los nodos del Document doc
		
				//crear el nodo y añadir un atributo
				Element empleado=doc.createElement("empleado");
				empleado.setAttribute("dni", dni);
				
				//Crear el nodo del nombre con su contenido y posteriormente lo añadimos
				Element nombreXml=doc.createElement("nombre");
				Text textnom=doc.createTextNode(nombre);
				nombreXml.appendChild(textnom);
				empleado.appendChild(nombreXml);
			
				
				//Crear el nodo del apellido con su contenido y posteriormente lo añadimos
				Element apellidoXml=doc.createElement("apellidos");
				Text textape=doc.createTextNode(apellido);
				apellidoXml.appendChild(textape);
				empleado.appendChild(apellidoXml);
			
				
				Element sueldoXml=doc.createElement("sueldo");
				empleado.appendChild(sueldoXml);
				
				Element baseXml=doc.createElement("base");
				sueldoXml.appendChild(baseXml);
				Text valorBase=doc.createTextNode(Float.toString(base));
				baseXml.appendChild(valorBase);
				
			
				Element compXml=doc.createElement("complementos");
				sueldoXml.appendChild(compXml);
				Text valorComp=doc.createTextNode(Float.toString(complementos));
				compXml.appendChild(valorComp);
				
				Element irpfXml=doc.createElement("irpf");
				sueldoXml.appendChild(irpfXml);
				Text valorIrpf=doc.createTextNode(Float.toString(irpf));
				irpfXml.appendChild(valorIrpf);
			
				//añadir el nodo al Document
				doc.getDocumentElement().appendChild(empleado);
			
			
			/*
			 * El Document no tiene formato y está en memoria. En necesario darle un formato
			 * y guardarlo en un fichero de texto, del tipo XML.
			 */
			Source source =new DOMSource(doc);
			Result result=new StreamResult(new File ("c:\\datos\\empleados2.xml"));
			
			//Transformación del Document al fichero
			Transformer trans=TransformerFactory.newInstance().newTransformer();
			trans.transform(source, result);
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			}
		  catch(Exception e ){
			e.printStackTrace();
			}
	}

}
