/*Uso_File: ejemplo de creación y borrado de ficheros, así como extrae información de él*/
import java.io.*;
public class Uso_File {
	public static void main(String[] args){
		File d=new File ("NuevoDirectorio"); //Directorio que creo a partir del actual
		File f1=new File (d,"Fichero1.txt");
		File f2=new File(d, "Fichero2.txt");
		File f4=new File(d, "Fichero4.txt");
		d.mkdir(); //Crear directorio
		try{
			if(f1.createNewFile())
				System.out.println("Fichero1 creado correctamente");
			else
				System.out.println("No se ha podido crear el Fichero1");
			if(f2.createNewFile())
				System.out.println("Fichero2 creado correctamente");
			else
				System.out.println("No se ha podido crear el Fichero2");
		} catch (IOException ioe) {ioe.printStackTrace();}
		try{
			File f3=new File("NuevoDirectorio/Fichero3.txt");
			f3.createNewFile(); //crea el Fichero3.txt en el nuevo directorio
		}catch (IOException ioe) {ioe.printStackTrace();}
		
		if(f4.delete()) //Comprobar mensaje cuando se borra un fichero que no existe
			System.out.println("Fichero4 borrado");
		else
			System.out.println("No se ha podido borrar el Fichero4");
		//Información sobre un fichero
		if(f1.exists()){
			System.out.println("Nombre del fichero: "+f1.getName());
			System.out.println("Ruta: "+f1.getPath());
			System.out.println("Ruta absoluta: "+f1.getAbsolutePath());
			System.out.println("Se puede escribir: "+f1.canRead());
			System.out.println("Se puede leer: "+f1.canWrite());
			System.out.println("Tamaño: "+f1.length());
		}
	}
}
