import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
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

public class ejer5 {
	public static void main(String[] args) {
		File fic = new File("c:\\datos\\empleados2.dat");
		InputStreamReader entrada = new InputStreamReader(System.in);
		BufferedReader teclado = new BufferedReader(entrada);
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
			Element raiz = doc.createElement("nominas");
			doc.appendChild(raiz);

			try {
				Empleados emp = new Empleados();
				RandomAccessFile ficale = new RandomAccessFile(fic, "rw");
				numeroRegistros = (int) (ficale.length() / emp.tamano());
				for (int x = 0; x < numeroRegistros; x++) {

					emp.leer(ficale);
					System.out.println("DNI: " + emp.getDni());
					System.out.println("Nombre : " + emp.getNombre());
					System.out.println("Apellidos: " + emp.getApellido());
					System.out.println("base: " + emp.getBase());
					System.out.println("Complementos: " + emp.getComp());
					System.out.println("irpf: " + emp.getIrpf());

					System.out.println();
					Element nodo = doc.createElement("nomina");
					nodo.setAttribute("dni", emp.getDni());

					Element baseXml = doc.createElement("base");
					Text valorBase = doc.createTextNode(Float.toString(emp
							.getBase()));
					baseXml.appendChild(valorBase);
					nodo.appendChild(baseXml);

					Element compXml = doc.createElement("complementos");
					Text valorComp = doc.createTextNode(Float.toString(emp
							.getComp()));
					compXml.appendChild(valorComp);
					nodo.appendChild(compXml);

					Element irpfXml = doc.createElement("irpf");
					Text valorIrpf = doc.createTextNode(Float.toString(emp
							.getIrpf()));
					irpfXml.appendChild(valorIrpf);
					nodo.appendChild(irpfXml);

					Element totalXml = doc.createElement("total");
					Text valortotal = doc.createTextNode(Float.toString((emp.getBase() + emp.getComp())-((emp.getBase() + emp.getComp()))* (emp.getIrpf())/ 100));
					totalXml.appendChild(valortotal);
					nodo.appendChild(totalXml);

					doc.getDocumentElement().appendChild(nodo);

					/*
					 * El Document no tiene formato y está en memoria. En
					 * necesario darle un formato y guardarlo en un fichero de
					 * texto, del tipo XML.
					 */
					Source source = new DOMSource(doc);
					Result result = new StreamResult(new File(
							"c:\\datos\\nominas.xml"));

					// Transformación del Document al fichero
					Transformer trans = TransformerFactory.newInstance()
							.newTransformer();
					trans.transform(source, result);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}