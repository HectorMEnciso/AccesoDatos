import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;



public class Ejercicio2 {
	public static void main(String[] args) {
		File departamentos = new File(
				"c:/datos/Departamentos.dat");

		Departamento depart = new Departamento();

		try {
			RandomAccessFile raf = new RandomAccessFile(departamentos, "rw");
			int numRegistros = (int) raf.length() / depart.tamano();//calcula numero de registros
			for (int i = 0; i < numRegistros; i++) {
				depart.leer(raf);//lee
				System.out.print(depart.getNumero() + " ");
				System.out.print(depart.getNombre() + " ");
				System.out.println(depart.getLocalidad());
			}
			System.out.println("========================================");

			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);

			System.out.println("¿Qué departamento desea modificar?");
			int num = Integer.parseInt(br.readLine());
			System.out.println("nuevo nombre para el departamento ");
			String nnombre = br.readLine();

			// Suponemos que están ordenados por número
			try {
				raf.seek(depart.tamano() * (num - 1) + 4);//se posiciona para escribir en el nombre(+4 ya que el entero ocupe los 4 primeros bytes)
			//se posiciona para leer viejo nombre
			char[] temp = new char[20];
			for (int i = 0; i < temp.length; i++)
				temp[i] = raf.readChar();
			String vnombre = new String(temp);//guarda viejo nombre
			
			raf.seek(depart.tamano() * (num - 1) + 4);//se vuelve a posicionar para escribir nuevo nombre
			
			StringBuffer sb = new StringBuffer(nnombre);
			sb.setLength(20);
			raf.writeChars(sb.toString());//escribe nuevo nombre

			System.out.println("Nombre antigüo: " + vnombre);
			System.out.println("Nuevos datos");
			
			raf.seek(0);//se posiciona al comienzo del fichero
			for (int i = 0; i < numRegistros; i++) {
				depart.leer(raf);//lee
				System.out.print(depart.getNumero() + " ");
				System.out.print(depart.getNombre() + " ");
				System.out.println(depart.getLocalidad());
			}
			} catch (EOFException e) {
				System.out.println("El departamento no existe");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
