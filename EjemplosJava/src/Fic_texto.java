//Ficheros_texto: permite leer los caracteres existentes en un fichero de tipo texto
import java.io.*;
public class Fic_texto {
	public static void main(String[] args) throws IOException {
		// declarar el fichero
		File fic = new File("fichero5.txt");

		try {
			FileReader ent = new FileReader(fic); //Se crea el flujo de entrada
			int i;
			while ((i = ent.read()) != -1) //Hasta fin de fichero
				System.out.print((char) i);
			ent.close(); // cerrar el flujo
		} catch(java.io.FileNotFoundException fioent) {
			System.out.println(fioent);
		}	
	}	
}
