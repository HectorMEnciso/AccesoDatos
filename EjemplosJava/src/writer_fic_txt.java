//Escribir_fic_texto: permite escribir caracteres en un fichero de tipo texto
import java.io.*;
public class writer_fic_txt {
	public static void main(String[] args) throws IOException {
		// declarar el fichero
		File fic = new File("C:\\datos\\fichero6.txt");// objeto fic de la clase file asociado al fichero6.txt
		try{
			FileWriter sal = new FileWriter(fic); //Se crea el flujo de salida sal asociado a fic
			//FileWriter sal = new FileWriter(fic, true); //Añadir caracteres al final
			String cadena="Esto es una prueba de la clase FileWriter";
			sal.write(cadena);
			/*char[] cad=cadena.toCharArray(); //convierte un string en array de caracteres
			for (int i=0;i<cad.length;i++)
				sal.write(cad[i]);
			sal.append("*"); // añade al final un *
*/			sal.close();
		}catch(java.io.FileNotFoundException fiosal) {
			System.out.println(fiosal);
		}
	}
}