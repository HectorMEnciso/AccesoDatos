import java.io.*;

public class Ejer1Java {
	public static void main(String[] args) throws IOException,ClassNotFoundException {
		
		Departamento departamento;
		String nombre, localidad;
		
		int numeroDep;
		
		File fic = new File("c:\\datos\\Departamentos.dat");

		FileOutputStream ficsal = new FileOutputStream(fic,true);// Crear el flujo de
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
		
	
		InputStreamReader entrada=new InputStreamReader(System.in);//entrada por teclado
		
		BufferedReader teclado=new BufferedReader(entrada);
		
		/*String nombres[] = { "Finanzas", "Recursos Humanos", "Logistica",
				"Marketing" };
		int numeros[] = { 15, 20, 15, 13};
		String localidades[] = { "Zaragoza Centro", "Zaragoza norte",
				"Zaragoza Sur", "Zaragoza Oeste" };*/
		
		System.out.print("Introduzca numero departamento: ");
		
		numeroDep=Integer.parseInt(teclado.readLine());//hay que hacer una conversion
				//cuando en un programa introducimos un datos 
				//y pulsamos el intro como final de entrada, el carácter intro 
				//también pasa al buffer de entrada
		//teclado.readLine();//capturamos el \n 
		System.out.print("Introduzca nombre departamento: ");
		
		nombre=teclado.readLine();
	
		System.out.print("Introduzca localidad departamento: ");
		
		localidad=teclado.readLine();
		
		departamento = new Departamento(numeroDep, nombre,localidad);// creo el objeto persona

		datsal.writeObject(departamento);// escribo la persona en el fichero
		
		datsal.close();
		// Leer el contenido de los objetos en el fichero binario
		try {
			int i;
			//departamento = (Departamento) datent.readObject();
			//System.out.println("Entro aqui");
			//departamento = (Departamento) datent.readObject();
			while (true) {// leer una pesona
				//System.out.println("i: "+i);
				//System.out.println("Entro while");
				departamento = (Departamento) datent.readObject();
				System.out.println("Nombre: " + departamento.getNombre()+ ", numero dep: "+ departamento.getNumeroDepartamento()+ ", localidad: " + departamento.getLocalidad());
				
			}
		} catch (EOFException eo) {
		}
		datent.close();
	}
}
