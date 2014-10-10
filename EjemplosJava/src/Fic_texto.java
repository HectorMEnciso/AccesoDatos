//Ficheros_texto: permite leer los caracteres existentes en un fichero de tipo texto
import java.io.*;

public class Fic_texto {
	public static void main(String[] args) throws IOException {
		// declarar el fichero
		File fic = new File("C:\\datos\\fichero5.txt");// objeto fic de la clase
														// file asociado al
														// fichero5.txt
		if (fic.length() == 0) {
			System.out.print("Fichero vacio");
		}
		try {
			FileReader ent = new FileReader(fic); // Se crea el flujo de entrada
													// ent asociado al objeto
													// fic
			int i;
			while ((i = ent.read()) != -1)
				// Read() lee un caracter y lo devuelve hasta que sea -1 (indica
				// fin fichero)

				System.out.print((char) i);
			ent.close(); // cerrar el flujo
		} catch (java.io.FileNotFoundException fioent) {// guardamos la
														// excepcion en fioent
														// (o cualquier
														// nombre)(indica
														// fichero no existe o
														// no válido)
			System.out.println(fioent);
		}
	}
}
