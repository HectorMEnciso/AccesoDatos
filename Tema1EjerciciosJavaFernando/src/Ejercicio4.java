import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;



public class Ejercicio4 {
	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		File departamentos = new File(
				"C://datos//Departamentos.dat");
		RandomAccessFile raf = new RandomAccessFile(departamentos, "rw");

		System.out.println("Introduce el nombre del nuevo departamento");
		String nombre = br.readLine();
		System.out.println("Introduce la localidad del nuevo departamento");
		String localidad = br.readLine();

		Departamento departamento = new Departamento();
		int numRegistros = (int) raf.length() / departamento.tamano();
		raf.seek(0);
		int i = 0;
		int numero = -1;
		try {
			while (i < numRegistros && numero != 0) {
				numero = raf.readInt();//lee numero Dep
				if (numero == 0) {
					// Introducir en la posición libre
					raf.seek(departamento.tamano() * (i));//se posiciona en la posicion de numeroDEp es 0
					departamento = new Departamento(i + 1, nombre, localidad);//construye objeto
					departamento.write(raf);//escribe objeto
				} else {
					raf.seek(departamento.tamano() * (i + 1));//si ninguno es cero se posiciona al final del registro
				}
				i++;
			}
			if(numero!=0){
				raf.seek(raf.length());
				departamento = new Departamento(numRegistros+1, nombre, localidad);
				departamento.write(raf);
			}
			raf.seek(0);
			numRegistros = (int) raf.length() / departamento.tamano();
			for (int j = 0; j < numRegistros; j++) {
				departamento.leer(raf);
				System.out.print(departamento.getNumero() + " ");
				System.out.print(departamento.getNombre() + " ");
				System.out.println(departamento.getLocalidad());
			}
		} catch (EOFException e) {
			raf.seek(raf.length());
			departamento = new Departamento(i + 1, nombre, localidad);
			departamento.write(raf);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
