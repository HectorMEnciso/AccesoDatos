//Inp_File_Random: ejemplo de lectura al contenido de un fichero aleatorio
import java.io.*;

public class Inp_File_Random {
	public static void main(String[] args) throws IOException {
		File fic = new File("c:\\datos\\Empleados.dat");
		// Se declara el fichero como de acceso aleatorio, para lectura y
		// escritura ("rw")
		// o para s�lo lectura ("r")
		RandomAccessFile ficale = new RandomAccessFile(fic, "r");

		// Recorrer el fichero para visualizar los registros guardados
		int idemp, depemp, posicion = 0;
		Double salemp;
		String apempl;
		char apemp[] = new char[10];

		for (;;) { // recorro el fichero aleatorio
			ficale.seek(posicion); // nos posicionamos en el inicio del fichero
			idemp = ficale.readInt(); // obtenemos el id del empleado
			apempl = LeerApellido(ficale, apemp);
			depemp = ficale.readInt(); // obtenemos el departamento
			salemp = ficale.readDouble(); // obtenemos el salario
		
			System.out.println("ID: " + idemp + ", Apellido: " + apempl
					+ ", Departamento: " + depemp + ", Salario: " + salemp);
			// Nos colocamos en el siguiente registro, sumando 36 bytes al puntero
			posicion = posicion + 36;
			// Controlamos el final del fichero
			if (ficale.getFilePointer() == ficale.length())
				break;
		}
		
		//Leer la posici�n de un empleado determinado
		int ident=6, idaux;
		posicion=(ident-1)*36;
		if (posicion >=ficale.length()){
			System.out.println("El empleado con identificador "+ident+ " no existe");}
			else
			{
				ficale.seek(posicion);
				idaux=ficale.readInt();
				apempl = LeerApellido(ficale, apemp);
				System.out.println("id: "+idaux+" Apellidos: "+apempl);
			}
		ficale.close();
	}

	private static String LeerApellido(RandomAccessFile ficale, char[] apemp)
			throws IOException {
		char aux;
		for (int i = 0; i < apemp.length; i++) {
			aux = ficale.readChar(); // recorrer uno a uno los caracteres del apellido
			apemp[i] = aux; // guardo el caracter le�do en el array
		}
		String apeString = new String(apemp); // convierto en String el array
		return apeString;
	}
}