import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;

public class LeerFicheroXML{
	public static void main(String arg[]) throws IOException {
		try {
			//Fabricar el documento
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse("c:\\datos\\clientes.xml");
			//
		
			Element raiz=doc.getDocumentElement();	//Obtener el nodo raíz del fichero
			
		
			NodeList listaClientes=raiz.getElementsByTagName("cliente");	//Obtener la lista de nodos que tienen la etiqueta "cliente"
			
			
			for (int i=0; i<listaClientes.getLength(); i++){//Recorrer la lista de los nodos encontrados
				
				
				Node cliente=listaClientes.item(i);//Obtener de la lista un cliente tras otro
				System.out.println("\nCliente nº: "+i);
				System.out.println("------------");
				
				Node atr=cliente.getAttributes().getNamedItem("nif");// Obtener el valor del atributo nif
				System.out.println(atr.getNodeName()+": "+atr.getNodeValue());
				
				NodeList datosCliente=cliente.getChildNodes();	// Obtener los elementos que compone el nodo cliente
				
		
				for (int j=0; j<datosCliente.getLength();j++){//Recorrer los elementos del nodo cliente
					
					Node nodoCliente=datosCliente.item(j);//Obtener de la lista un elemento tras otro
				
					if (nodoCliente.getNodeType()==Node.ELEMENT_NODE){//Comprobar que el elemento se trata de uno del tipo Elemento
						//Mostrar del nombre del tipo del elemento
						//System.out.println(nodoCliente.getNodeName()+": ");
						//Obtener el valor contenido en el elemento
						Node datoElemento=nodoCliente.getFirstChild();
						//Mostrar el contenido del atributo
						if (datoElemento!=null && datoElemento.getNodeType()==Node.TEXT_NODE){
							System.out.println(nodoCliente.getNodeName()+": "+datoElemento.getNodeValue());
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
