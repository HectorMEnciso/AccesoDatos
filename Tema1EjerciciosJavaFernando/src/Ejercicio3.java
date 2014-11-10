import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;



public class Ejercicio3 {
	public static void main(String[] args) {
		File departamentos = new File(
				"C://datos//Departamentos.dat");

		Departamento depart = new Departamento();

		try {
			RandomAccessFile raf = new RandomAccessFile(departamentos, "rw");
			int numRegistros = (int) raf.length() / depart.tamano();
			for (int i = 0; i < numRegistros; i++) {
				depart.leer(raf);
				System.out.print(depart.getNumero() + " ");
				System.out.print(depart.getNombre() + " ");
				System.out.println(depart.getLocalidad());
			}
			System.out.println("========================================");

			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);

			System.out.println("¿Qué departamento desea eliminar?");
			int num = Integer.parseInt(br.readLine());

			raf.seek(depart.tamano() * (num-1));//se posiciona en la posicion introducida
			
			byte[] borrado = new byte[depart.tamano()];//crea vector vacio de tamaño igual al registro
			raf.write(borrado);//rellena el hueco con ese vector vacio
			raf.seek(0);
			for (int i = 0; i < numRegistros; i++) {
				depart.leer(raf);
				System.out.print(depart.getNumero() + " ");
				System.out.print(depart.getNombre() + " ");
				System.out.println(depart.getLocalidad());
			}
			
			
		} catch (EOFException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
