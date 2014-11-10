import java.io.*;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class ejer2 {
	public static void main(String arg[]) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String nombre, apellido;
		try {

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse("c:\\datos\\alumnos.xml");
			//
			// Obtener el nodo raíz del fichero
			Element raiz = doc.getDocumentElement();

			try {
				System.out.print("Introduzca nombre: ");
				nombre = br.readLine();
				System.out.print("Introduzca apellido: ");
				apellido = br.readLine();

				Element alumno = doc.createElement("alumno");
				Element nombreXml = doc.createElement("nombre");
				Text textnom = doc.createTextNode(nombre);
				nombreXml.appendChild(textnom);
				alumno.appendChild(nombreXml);
				
				Element apellidoXml = doc.createElement("apellido");
				Text textape = doc.createTextNode(apellido);
				apellidoXml.appendChild(textape);
				alumno.appendChild(apellidoXml);

				doc.getDocumentElement().appendChild(alumno);

				Source source = new DOMSource(doc);
				Result result = new StreamResult(new File(
						"c:\\datos\\alumnos.xml"));

				// Transformación del Document al fichero
				Transformer trans = TransformerFactory.newInstance()
						.newTransformer();
				trans.transform(source, result);

			} catch (Exception e) {
				e.printStackTrace();
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
	}
}