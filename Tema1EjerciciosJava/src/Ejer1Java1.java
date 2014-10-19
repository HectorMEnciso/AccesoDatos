import java.io.*;

public class Ejer1Java1 {
	public static void main(String[] args) throws IOException,
			ClassNotFoundException {
		File fic = new File("c:\\datos\\Departamentos.dat");

		String nombre, localidad;

		int numeroDep;
		RandomAccessFile ficale = new RandomAccessFile(fic, "rw");

		InputStreamReader entrada = new InputStreamReader(System.in);// entrada
																		// por
																		// teclado
		BufferedReader teclado = new BufferedReader(entrada);

		/*
		 * System.out.print("Introduzca nombre departamento: "); nombre =
		 * teclado.readLine();
		 * 
		 * System.out.print("Introduzca localidad departamento: "); localidad =
		 * teclado.readLine();
		 */

		String nombres[] = { "Fernandez", "Gil", "Lopez", "Ramos", "Sevilla",
				"Casilla", "Rey" };
		// int numeroDepartamento[] = { 1, 2, 3, 4, 5 ,6,7};
		String localidades[] = { "ZaragozaCentro", "Zaragozanorte",
				"ZaragozaSur", "ZaragozaOeste", "Huesca", "Teruel", "Calatayud" };

		char caracteresNombre[] = new char[10];
		char caracteresLocalidad[] = new char[20];
		
		StringBuffer buffer1 = null; // buffer para almacenar apellido
		StringBuffer buffer2 = null;
		int n = nombres.length; // calcular el número de elementos del array
		for (int i = 0; i < n; i++) {
			ficale.writeInt(i + 1); // uso i+1 para identificar a cada empleado
			buffer1 = new StringBuffer(nombres[i]);
			buffer2 = new StringBuffer(localidades[i]);
			buffer1.setLength(10); // asigno 10 caracteres fijo al apellido
			buffer2.setLength(20); // asigno 10 caracteres fijo al apellido
			ficale.writeChars(buffer1.toString()); // inserto el apellido
			ficale.writeChars(buffer2.toString()); // inserto el salario
		}

		int posicion = 0;
		for (;;) { // recorro el fichero aleatorio
			ficale.seek(posicion); // nos posicionamos en el inicio del fichero
			numeroDep = ficale.readInt(); // obtenemos el id
			nombre = LeerNombre(ficale, caracteresNombre);// obtenemos nombre
			localidad = LeerNombre(ficale, caracteresLocalidad);; // obtenemos el localidad

			System.out.println("ID: " + numeroDep + ", Nombre: " + nombre
					+ ", Localidad: " + localidad);

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

			localidad = LeerNombre(ficale, caracteresLocalidad);// obtenemos nombre
			System.out.println("id: " + numeroDep + " Nombre: " + nombre
					+ "Localidad: " + localidad);
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