//File_Random: ejemplo de escritura en un fichero aleatorio
import java.io.*;

public class File_Random {
	public static void main(String[] args) throws IOException {
		File fic = new File("c:\\datos\\Empleados.dat");
		// Se declara el fichero como de acceso aleatorio, para lectura y
		// escritura ("rw")
		// o para sólo lectura ("r")
		RandomAccessFile ficale = new RandomAccessFile(fic, "rw");

		// Definimos los arrays con los datos. La longitud de cada registro es 36 bytes:

		String apellido[] = { "Fernandez", "Gil", "Lopez", "Ramos", "Sevilla",
				"Casilla", "Rey" };
		int dep[] = { 10, 20, 10, 10, 30, 30, 20 };
		Double salario[] = { 1000.45, 2300.60, 3000.0, 1500.45, 1200.50,
				2500.90, 3000.00 };

		StringBuffer buffer = null; // buffer para almacenar apellido
		int n = apellido.length; // calcular el número de elementos del array
		for (int i = 0; i < n; i++) {
			ficale.writeInt(i + 1); // uso i+1 para identificar a cada empleado
			buffer = new StringBuffer(apellido[i]);
			buffer.setLength(10); // asigno 10 caracteres fijo al apellido
			ficale.writeChars(buffer.toString()); // inserto el apellido
			ficale.writeInt(dep[i]); // inserto el numero departmamento
			ficale.writeDouble(salario[i]); // inserto el salario
		}
		
		//Añadir un nuevo registro en una posicion determinada
		StringBuffer buffer1=null;
		String apellido1="Gonzalez";
		Double salario1=4000.00;
		int id1=20;
		int dep1=20;
		long posicion=(id1-1)*36; //Calcular la posicion
		ficale.seek(posicion); //Colocar el puntero
		ficale.writeInt(id1);
		buffer1=new StringBuffer(apellido1);
		buffer1.setLength(10);
		ficale.writeChars(buffer1.toString());
		ficale.writeInt(dep1);
		ficale.writeDouble(salario1);
		ficale.close();
	}
}
