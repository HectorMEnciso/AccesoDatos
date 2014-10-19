import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

public class Ejer2Java {
	public static void main(String[] args) throws IOException,
			ClassNotFoundException {
		File fic = new File("c:\\datos\\Departamentos.dat");

		String nombre, localidad, nombre1, localidad1;

		int numeroDep;
		RandomAccessFile ficale = new RandomAccessFile(fic, "rw");

		InputStreamReader entrada = new InputStreamReader(System.in);// entrada
																		// por
		char caracteresNombre[] = new char[10];
		char caracteresLocalidad[] = new char[20]; // teclado
		BufferedReader teclado = new BufferedReader(entrada);

		int posicion = 0;
		for (;;) { // recorro el fichero aleatorio
			ficale.seek(posicion); // nos posicionamos en el inicio del fichero
			numeroDep = ficale.readInt(); // obtenemos el id
			nombre = LeerNombre(ficale, caracteresNombre);// obtenemos nombre
			localidad = LeerNombre(ficale, caracteresLocalidad);
			// obtenemos el localidad

			// System.out.println("ID: " + numeroDep + ", Nombre: " + nombre
			// + ", Localidad: " + localidad);

			posicion = posicion + 64; // Nos colocamos en el siguiente registro,
										// sumando 64 bytes al puntero

			if (ficale.getFilePointer() == ficale.length())// Controlamos el
															// final del fichero
				break;
		}
		// Leer la posición de un empleado determinado
		System.out.print("Introduzca numero departamento: ");
		numeroDep = Integer.parseInt(teclado.readLine());
		posicion = (numeroDep - 1) * 64;
		if (posicion >= ficale.length()) {
			System.out.println("El dep  " + numeroDep + " no existe");
		} else {
			ficale.seek(posicion);
			numeroDep = ficale.readInt();
			nombre = LeerNombre(ficale, caracteresNombre);// obtenemos nombre

			localidad = LeerNombre(ficale, caracteresLocalidad);// obtenemos
																// nombre
			System.out.println("id: " + numeroDep + " Nombre: " + nombre
					+ "Localidad: " + localidad);

			System.out.print("Introduzca nuevo nombre departamento: ");

			nombre = teclado.readLine();

			System.out.print("Introduzca nueva localidad departamento: ");

			localidad = teclado.readLine();

			StringBuffer buffer1 = null; // buffer para almacenar apellido
			StringBuffer buffer2 = null;
			// calcular el número de elementos del array
			ficale.seek(posicion+4);
			//ficale.writeInt(numeroDep);
			buffer1 = new StringBuffer(nombre);
			buffer2 = new StringBuffer(localidad);
			buffer1.setLength(10); // asigno 10 caracteres fijo al apellido
			buffer2.setLength(20); // asigno 10 caracteres fijo al apellido
			ficale.writeChars(buffer1.toString()); // inserto el apellido
			ficale.writeChars(buffer2.toString()); // inserto el salario
			
			ficale.seek(posicion);
			numeroDep = ficale.readInt();
			nombre1 = LeerNombre(ficale, caracteresNombre);// obtenemos nombre

			localidad1 = LeerNombre(ficale, caracteresLocalidad);// obtenemos
																// nombre
			System.out.println("id: " + numeroDep + " Nombre: " + nombre1
					+ "Localidad: " + localidad1);

		}
		ficale.close();

	}

	private static String LeerNombre(RandomAccessFile ficale, char[] caracteres)
			throws IOException {
		
		char aux;
		for (int i = 0; i < caracteres.length; i++) {
			aux = ficale.readChar(); // recorrer uno a uno los caracteres del
										// apellido
			caracteres[i] = aux; // guardo el caracter leído en el array
		}
		String apeString = new String(caracteres); // convierto en String el
													// array
		return apeString;
	}
}
