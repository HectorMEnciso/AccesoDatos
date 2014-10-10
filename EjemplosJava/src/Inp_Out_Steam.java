//Inp_Out_Stream: ejemplo de lectura y escritura sobre un fichero binario
import java.io.*;

public class Inp_Out_Steam {
	public static void main(String[] args) throws IOException {
		
		File fic = new File("c:\\datos\\ficbinario.dat");//objeto fic asociado a ficbinario.dat
		
		FileOutputStream ficsal = new FileOutputStream(fic);// Crear el flujo de escritura fiscsal asociado a ficbinario.dat
		
		/*Para añadir bytes al final del fichero utilizar 
		 * FileOutputStream  ficsal = new FileOutputStream(fic, true); */
	
		
		FileInputStream ficent = new FileInputStream(fic);// Crear el flujo de lectura ficent asociado a ficbinario.dat
		int i;

		for (i = 1; i <= 100; i++) {
			ficsal.write(i);
		}
		ficsal.close(); // cerrar flujo de salida ficsal

		
		while ((i = ficent.read()) != -1)// visualizar el contenido del fichero hasta final de dicho fichero
		
			System.out.println(i);	// lee datos del flujo hasta llegar al final
		ficent.close();// cerrar flujo de entrada ficent
		}
}
