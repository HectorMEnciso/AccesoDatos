//IO_obj_serializable: ejemplo para guardar y leer objetos en un fichero binario
import java.io.*;

public class IO_obj_serializable {
	public static void main(String[] args) throws IOException,ClassNotFoundException {
		
		Persona persona; // Defino la variable persona

		File fic = new File("c:\\datos\\personas.dat");
	
		FileOutputStream ficsal = new FileOutputStream(fic);// Crear el flujo de salida y entrada
		FileInputStream ficent = new FileInputStream(fic);

		
		ObjectOutputStream datsal = new ObjectOutputStream(ficsal);// Conectar el flujo de bytes al de salida y al de entrada
		ObjectInputStream datent = new ObjectInputStream(ficent);

		String nombres[] = { "Juan", "Ana", "Carlos", "Alicia", "Carmen","Jesús" };
		int edades[] = { 15, 20, 15, 13, 40, 23 };

		for (int i = 0; i < edades.length; i++) { // recorro los arrays
			
			persona = new Persona(nombres[i], edades[i]);// creo el objeto persona
	
			datsal.writeObject(persona);// escribo la persona en el fichero
		}
		datsal.close(); // cerrar el stream de salida

		// Leer el contenido de los objetos en el fichero binario
		try {
			while (true) {
		
				persona = (Persona) datent.readObject();// leer una pesona
				System.out.println("Nombre: " + persona.getNombre()+ ", edad: " + persona.getEdad());
			}
		} catch (EOFException eo) {
		}
		datent.close();
	}
}
