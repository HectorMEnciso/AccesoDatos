/*Crear un fichero XML con DOM*/
import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;


public class CrearFicheroXML {
	public static void main(String arg[]) throws IOException {
		
		String nombres[]={"Alberto", "Ana","Luis","Maria"};//Crear dos vectores dónde tenemos los datos guardados para el XML
		String apellidos[]={"Perez Ibañez","Domingo Dominguez", "Abad Ruiz","Sanz Perez"};
		
		/*Crear una instancia de DocumentBuilderFactory para construir el parser.
		 * Se debe encerrarse entre try-catch porque genera la excepción ParserConfigurationException
		 */
		
		try {
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();//creamos instancia para construir el parser
			DocumentBuilder db = dbf.newDocumentBuilder();//creamos constructor para el documento
			Document doc = db.newDocument();//creamos el documento
			//Document doc=DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			
			/*
			 * Tenemos un Document en memoria que representa el árbol del ficherl XML.
			 * Este árbol está vacío, el primer paso es asignar la versión de XML y el nodo raíz
			 */
			doc.setXmlVersion("1.0");
			Element raiz=doc.createElement("empleados");//creamos cual va a ser el elemento raiz
			doc.appendChild(raiz);//añadimos elemento raiz al documento doc
			
			//Procedemos a rellenar cada uno de los nodos del Document doc
			for (int i=0;i<nombres.length;i++){
			
				Element empleado=doc.createElement("empleado"); //creamos un nodo nuevo llamado empleado
				empleado.setAttribute("id", Integer.toString(i));//para el nodo creado anteriormente creamos un atributo id
				
				//Crear el nodo del nombre con su contenido y posteriormente lo añadimos
				Element nombre=doc.createElement("nombre");//creamos otro nodo nombre
				Text textnom=doc.createTextNode(nombres[i]);//establecemos valor para los nombres
				nombre.appendChild(textnom);//le añadimos al nodo nombre valores
				nombre.setAttribute("dep", "contabilidad");//establecemos el atributo dep y como valor contabilidad
				empleado.appendChild(nombre);//añadimos el nodo nombre al nodo empleado
				
				
				//Crear el nodo del apellido con su contenido y posteriormente lo añadimos
				Element apellido=doc.createElement("apellidos");
				Text textape=doc.createTextNode(apellidos[i]);
				apellido.appendChild(textape);
				empleado.appendChild(apellido);
				
				
				
				doc.getDocumentElement().appendChild(empleado);//añadir el nodo al Document
			}
			
			/*
			 * El Document no tiene formato y está en memoria. En necesario darle un formato
			 * y guardarlo en un fichero de texto, del tipo XML.
			 */
			Source source =new DOMSource(doc);
			Result result=new StreamResult(new File ("c:\\datos\\empleados.xml"));//archivo de destino
			
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
