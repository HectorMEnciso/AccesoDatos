import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
public class Leer {
	public static void main(String arg[]) throws IOException {
		try {
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse("c:\\datos\\empleados.xml");
			
			Element raiz=doc.getDocumentElement();
			
			NodeList listaEmpleados=raiz.getElementsByTagName("empleado");
			
			for (int i=0; i<listaEmpleados.getLength(); i++){
				
				Node empleado=listaEmpleados.item(i);
				System.out.println("\nEmpleado nº: "+i);
				
				Node atr=empleado.getAttributes().getNamedItem("id");
				System.out.println(atr.getNodeName()+": "+atr.getNodeValue());
				
				NodeList datosEmpleado=empleado.getChildNodes();
				
				for (int j=0; j<datosEmpleado.getLength();j++){
					
					Node nodoEmpleado=datosEmpleado.item(j);
					if (nodoEmpleado.getNodeName()=="nombre"){
						Node atributo=nodoEmpleado.getAttributes().getNamedItem("dep");
						System.out.println(atributo.getNodeName()+": "+atributo.getNodeValue());
					}
					
					if (nodoEmpleado.getNodeType()==Node.ELEMENT_NODE){
						Node datoElemento=nodoEmpleado.getFirstChild();
						
						if (datoElemento!=null && datoElemento.getNodeType()==Node.TEXT_NODE){
							System.out.println(nodoEmpleado.getNodeName()+": "+datoElemento.getNodeValue());
			
						}
					}
				}
			}
			
		}catch (IOException ex){
			System.out.println("ERROR: no se ha podido leer el fichero "+ex.getMessage());
			ex.printStackTrace();
		}catch(ParserConfigurationException ex){
			System.out.println("ERROR: No se ha podido crear el generador de documentos XML "+ex.getMessage());
			ex.printStackTrace();
		}catch (SAXException ex){
			System.out.println("ERROR: el formato XML del fichero no es correcto "+ex.getMessage());
			ex.printStackTrace();
		}
}
}