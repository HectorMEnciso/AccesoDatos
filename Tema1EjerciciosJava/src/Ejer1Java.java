import java.io.*;

public class Ejer1Java {
	public static void main(String[] args) throws IOException,
			ClassNotFoundException {
		Departamento departamento;

		File fic = new File("c:\\datos\\Departamentos.dat");

		FileOutputStream ficsal = new FileOutputStream(fic);// Crear el flujo de
															// salida y entrada
		FileInputStream ficent = new FileInputStream(fic);

		ObjectOutputStream datsal = new ObjectOutputStream(ficsal);// Conectar
																	// el flujo
																	// de bytes
																	// al de
																	// salida y
																	// al de
																	// entrada
		ObjectInputStream datent = new ObjectInputStream(ficent);

		String nombres[] = { "Finanzas", "Recursos Humanos", "Logistica",
				"Marketing" };
		int numeros[] = { 15, 20, 15, 13};
		String localidades[] = { "Zaragoza Centro", "Zaragoza norte",
				"Zaragoza Sur", "Zaragoza Oeste" };

		for (int i = 0; i < numeros.length; i++) { // recorro los arrays

			departamento = new Departamento(numeros[i], nombres[i],
					localidades[i]);// creo el objeto persona

			datsal.writeObject(departamento);// escribo la persona en el fichero
		}
		datsal.close();
		// Leer el contenido de los objetos en el fichero binario
		try {
			while (true) {

				departamento = (Departamento) datent.readObject();// leer una
																	// pesona
				System.out.println("Nombre: " + departamento.getNombre()
						+ ", numero dep: "
						+ departamento.getNumeroDepartamento()
						+ ", localidad: " + departamento.getLocalidad());
			}
		} catch (EOFException eo) {
		}
		datent.close();
	}
}
