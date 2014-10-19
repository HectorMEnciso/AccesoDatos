import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

public class Ejer4Java {
	public static void main(String[] args) throws IOException,
			ClassNotFoundException {
		File fic = new File("c:\\datos\\Departamentos.dat");

		String nombre, localidad;
		int numeroRegistros, vector[];
		numeroRegistros=(int) (fic.length()/64);
		System.out.print(numeroRegistros);
		vector=new int[numeroRegistros];
		int numeroDep;
		RandomAccessFile ficale = new RandomAccessFile(fic, "rw");
		boolean hayHueco=false;
		InputStreamReader entrada = new InputStreamReader(System.in);// entrada
		//int vector[] = null;																// por
		char caracteresNombre[] = new char[10];
		char caracteresLocalidad[] = new char[20]; // teclado
		BufferedReader teclado = new BufferedReader(entrada);
		int posicion = 0,pos = 0;
		for (;;) { // recorro el fichero aleatorio
			ficale.seek(posicion); // nos posicionamos en el inicio del fichero
			numeroDep = ficale.readInt(); // obtenemos el id
			nombre = LeerNombre(ficale, caracteresNombre);// obtenemos nombre
			localidad = LeerNombre(ficale, caracteresLocalidad);
			posicion = posicion + 64; // Nos colocamos en el siguiente registro,
			for(int x=0;x<vector.length;x++){
				
			vector[x]=	numeroDep;	
			if(vector[x]==0){
				 pos=x;
				hayHueco=true;
			}
			}
			//calcular numero de registros; numero total de bytes del fichero (length) / bytes de cada registro
			
			if (ficale.getFilePointer() == ficale.length())// Controlamos el
															// final del fichero
				break;
		}
		
		System.out.print("Introduzca nombre departamento: ");

		nombre = teclado.readLine();

		System.out.print("Introduzca localidad departamento: ");

		localidad = teclado.readLine();
	
		if(hayHueco){
			
			StringBuffer buffer1 = null; // buffer para almacenar apellido
			StringBuffer buffer2 = null;
			// calcular el número de elementos del array
			posicion=(pos-1)*64;
			ficale.seek(posicion);
			ficale.writeInt(pos);
			buffer1 = new StringBuffer(nombre);
			buffer2 = new StringBuffer(localidad);
			buffer1.setLength(10); // asigno 10 caracteres fijo al apellido
			buffer2.setLength(20); // asigno 10 caracteres fijo al apellido
			ficale.writeChars(buffer1.toString()); // inserto el apellido
			ficale.writeChars(buffer2.toString());
			ficale.seek(posicion);
			numeroDep = ficale.readInt();
			nombre = LeerNombre(ficale, caracteresNombre);// obtenemos nombre

			localidad = LeerNombre(ficale, caracteresLocalidad);// obtenemos
																// nombre
			System.out.println("id: " + numeroDep + " Nombre: " + nombre
					+ "Localidad: " + localidad);
		}
		else{
			StringBuffer buffer1 = null; // buffer para almacenar apellido
			StringBuffer buffer2 = null;
			// calcular el número de elementos del array
			ficale.seek(posicion);
			ficale.writeInt(numeroRegistros+1);
			buffer1 = new StringBuffer(nombre);
			buffer2 = new StringBuffer(localidad);
			buffer1.setLength(10); // asigno 10 caracteres fijo al apellido
			buffer2.setLength(20); // asigno 10 caracteres fijo al apellido
			ficale.writeChars(buffer1.toString()); // inserto el apellido
			ficale.writeChars(buffer2.toString());
			ficale.seek(posicion);
			numeroDep = ficale.readInt();
			nombre = LeerNombre(ficale, caracteresNombre);// obtenemos nombre

			localidad = LeerNombre(ficale, caracteresLocalidad);// obtenemos
																// nombre
			System.out.println("id: " + numeroDep + " Nombre: " + nombre
					+ "Localidad: " + localidad);
		}
		posicion=0;
		for (;;) { // recorro el fichero aleatorio
			ficale.seek(posicion); // nos posicionamos en el inicio del fichero
			numeroDep = ficale.readInt(); // obtenemos el id
			nombre = LeerNombre(ficale, caracteresNombre);// obtenemos nombre
			localidad = LeerNombre(ficale, caracteresLocalidad);
			posicion = posicion + 64; // Nos colocamos en el siguiente registro,
										// sumando 64 bytes al puntero

			if (ficale.getFilePointer() == ficale.length())// Controlamos el
															// final del fichero
				break;
		}
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