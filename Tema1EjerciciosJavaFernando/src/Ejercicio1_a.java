import java.io.File;
import java.io.*;

public class Ejercicio1_a {
	public static void main(String[] args) {
		Departamento departamento;
		String[] nombres = { "nombre1", "nombre2", "nombre3", "nombre4",
				"nombre5", "nombre6" };
		String[] localidades = { "Zaragoza", "Calatayud", "Zaragoza", "Huesca",
				"Teruel", "Madrid" };
		File departamentos = new File("c://datos//Departamentos.dat");
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(departamentos, true));
			for (int i = 0; i < 6; i++) {
				departamento = new Departamento(i + 1, nombres[i],
						localidades[i]);
				oos.writeObject(departamento);
			}
			departamento = new Departamento(10, "nombre10","Madrid");
			oos.writeObject(departamento);
			oos.close();
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					departamentos));
			
			while(true){
				departamento = (Departamento) ois.readObject();
				System.out.print(departamento.getNumero() + " "
						+ departamento.getNombre() + " "
						+ departamento.getLocalidad() + "\n");
			}
			
		/*	
			for (int i = 0; i < 5; i++) {
				departamento = (Departamento) ois.readObject();
				System.out.print(departamento.getNumero() + " "
						+ departamento.getNombre() + " "
						+ departamento.getLocalidad() + "\n");
			}*/
		} catch (Exception e) {

		}
	}
}
