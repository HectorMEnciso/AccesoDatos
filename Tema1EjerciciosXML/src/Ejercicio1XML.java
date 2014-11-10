/*Crear un fichero XML con DOM*/
import java.io.*;


import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class Ejercicio1XML {
	public static void main(String arg[]) throws IOException {

		File departamentos = new File("c:\\datos\\Departamento.dat");

		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();

			doc.setXmlVersion("1.0");
			Element raiz = doc.createElement("departamentos");
			doc.appendChild(raiz);

			// Procedemos a rellenar cada uno de los nodos del Document doc
			try {
				Departamento departamento = new Departamento();
				RandomAccessFile raf = new RandomAccessFile(departamentos, "rw");
				int numreg = (int) (raf.length() / departamento.tamano());
				for (int i = 0; i < numreg; i++) {
					departamento.leer(raf);
					if (departamento.getNumeroDepartamento() != 0) {

						Element nodo = doc.createElement("departamento");
						nodo.setAttribute("id",
								Integer.toString(departamento.getNumeroDepartamento()));

						Element nombre = doc.createElement("nombre");
						Text textnom = doc.createTextNode(departamento
								.getNombre().trim());
						nombre.appendChild(textnom);
						nodo.appendChild(nombre);

						Element localidad = doc.createElement("localidad");
						Text textape = doc.createTextNode(departamento
								.getLocalidad().trim());
						localidad.appendChild(textape);
						nodo.appendChild(localidad);
						
						// añadir el nodo al Document
						doc.getDocumentElement().appendChild(nodo);
					}

				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			/*
			 * El Document no tiene formato y está en memoria. En necesario
			 * darle un formato y guardarlo en un fichero de texto, del tipo
			 * XML.
			 */
			Source source = new DOMSource(doc);
			Result result = new StreamResult(new File(
					"c:\\datos\\prueba.xml"));

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
