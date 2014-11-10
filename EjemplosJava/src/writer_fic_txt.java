//Escribir_fic_texto: permite escribir caracteres en un fichero de tipo texto
import java.io.*;
public class writer_fic_txt {
	public static void main(String[] args) throws IOException {
		// declarar el fichero
		File fic = new File("fichero6.txt");
		try{
			FileWriter sal = new FileWriter(fic); //Se crea el flujo de salida
			//FileWriter sal = new FileWriter(fic, true); //Añadir caracteres al final
			String cadena="Esto es una prueba de la clase Writer";
			sal.write(cadena);
			sal.append("*"); // añade al final un *
			sal.close();
		}catch(java.io.FileNotFoundException fiosal) {
			System.out.println(fiosal);
		}
	}
}