//Ejemplo de BufferedReader
import java.io.*;
public class buffered_reader {
	public static void main(String[] args) {
		try{
			BufferedReader fichero = new BufferedReader(new FileReader ("c:\\datos\\fichero8.txt"));
			String linea;
			while ((linea=fichero.readLine())!=null)
				System.out.println(linea);
			fichero.close();
		}catch (FileNotFoundException fn) {
			System.out.println(fn);
			}
		catch (IOException io){
			System.out.println("Error de E/S");
		}
	}
}
