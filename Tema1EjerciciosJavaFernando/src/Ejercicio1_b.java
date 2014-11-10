import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;


public class Ejercicio1_b {
	public static void main(String[] args){
		Departamento departamento;
		String[] nombres = {"nombre1","nombre2","nombre3","nombre4","nombre5"};
		String[] localidades = {"Zaragoza","Calatayud","Zaragoza","Huesca","Teruel"};
		File departamentos = new File("c:\\datos\\Departamento.dat");
		try {
			RandomAccessFile raf = new RandomAccessFile(departamentos, "rw");
			for(int i=0;i<5;i++){
				departamento = new Departamento(i+1, nombres[i], localidades[i]);//construye objeto
				departamento.write(raf);//escribe en el fichero
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
