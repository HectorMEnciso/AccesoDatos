import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

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

public class ejer1 {
	public static void main(String arg[]) throws IOException {
		File fic = new File("c:\\datos\\Departamento.dat");
	
		int numeroRegistros;
		try {

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			// Document
			// doc=DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

			/*
			 * Tenemos un Document en memoria que representa el árbol del
			 * ficherl XML. Este árbol está vacío, el primer paso es asignar la
			 * versión de XML y el nodo raíz
			 */
			doc.setXmlVersion("1.0");
			Element raiz = doc.createElement("departamentos");
			doc.appendChild(raiz);

			try {
				Departamento depart = new Departamento();
				RandomAccessFile ficale = new RandomAccessFile(fic, "rw");
				numeroRegistros = (int) (ficale.length() / depart.tamano());
				for (int x = 0; x < numeroRegistros; x++) {

					depart.leer(ficale);
					System.out.print("ID: " + depart.getNumeroDepartamento());
					System.out.print(" Nombre : " + depart.getNombre().trim());
					System.out.print("Localidad: " + depart.getLocalidad().trim());
					System.out.println();

					// Procedemos a rellenar cada uno de los nodos del Document
					// doc

					// crear el nodo y añadir un atributo
					Element departamento = doc.createElement("departamento");
					departamento.setAttribute("id", Integer.toString(x + 1).trim());//xml solo guarda string

					// Crear el nodo del nombre con su contenido y
					// posteriormente lo
					// añadimos
					Element nombre = doc.createElement("nombre");
					Text textnom = doc.createTextNode(depart.getNombre().trim());
					nombre.appendChild(textnom);
					departamento.appendChild(nombre);
					

					// Crear el nodo del apellido con su contenido y
					// posteriormente
					// lo añadimos
					Element localidad = doc.createElement("localidad");
					Text textape = doc.createTextNode(depart.getLocalidad().trim());
					localidad.appendChild(textape);
					departamento.appendChild(localidad);
					

					// añadir el nodo al Document
					doc.getDocumentElement().appendChild(departamento);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("Error fichero");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error fichero2");
			}

			/*
			 * El Document no tiene formato y está en memoria. En necesario
			 * darle un formato y guardarlo en un fichero de texto, del tipo
			 * XML.
			 */
			Source source = new DOMSource(doc);
			Result result = new StreamResult(new File(
					"c:\\datos\\Departamentos.xml"));

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
}
