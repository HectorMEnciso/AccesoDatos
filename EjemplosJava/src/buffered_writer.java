import java.io.*;

public class buffered_writer {
	public static void main(String[] args) {
		try {
			BufferedWriter fichero = new BufferedWriter(new FileWriter("c:\\datos\\fichero8.txt"));//objeto fichero asociado a fichero8.txt
			for (int i = 1; i < 11; i++) {
				fichero.write("Fila numero: " + i);
				fichero.newLine(); // salto de linea
			}
			fichero.close();//cerramos flujo de escritura
		} catch (FileNotFoundException fn) {
			System.out.println("No se encuentra el fichero");
		} catch (IOException io) {
			System.out.println("Error de E/S");
		}
	}
}
