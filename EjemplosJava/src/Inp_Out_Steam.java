//Inp_Out_Stream: ejemplo de lectura y escritura sobre un fichero binario
import java.io.*;

public class Inp_Out_Steam {
	public static void main(String[] args) throws IOException {
		File fic = new File("c:\\datos\\ficbinario.dat");

		FileOutputStream ficsal = new FileOutputStream(fic);// Crear el flujo de escritura en el fichero binario
		
		/*Para añadir bytes al final del fichero utilizar 
		 * FileOutputStream  ficsal = new FileOutputStream(fic, true); */
	
	
		FileInputStream ficent = new FileInputStream(fic);// Crear el flujo de lectura  del fichero
		int i;

		for (i = 1; i <= 100; i++) 
		ficsal.write(i);
		ficsal.close(); // cerrar flujo de salida

		// visualizar el contenido del fichero
		while ((i = ficent.read()) != -1)
			// lee datos del flujo hasta llegar al final
			System.out.println(i);
		ficent.close();
	}
}
