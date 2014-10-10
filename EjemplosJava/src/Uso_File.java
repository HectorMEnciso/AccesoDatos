/*Uso_File: ejemplo de creación y borrado de ficheros, así como extrae información de él*/
import java.io.*;
public class Uso_File {
	public static void main(String[] args){
		File d=new File ("NuevoDirectorio"); //Directorio que creo a partir del actual
		File f1=new File (d,"Fichero1.txt");/*Objetos de clase file	*/
		File f2=new File(d, "Fichero2.txt");
		File f4=new File(d, "Fichero4.txt");
		d.mkdir(); //Crear directorio con el nombre asociado al obejto d de la clase file
		try{
			if(f1.createNewFile())//crea un fichero vacio si no existe
				System.out.println("Fichero1 creado correctamente");
			else
				System.out.println("No se ha podido crear el Fichero1");
			if(f2.createNewFile())//crea un fichero vacio si no existe
				System.out.println("Fichero2 creado correctamente");
			else
				System.out.println("No se ha podido crear el Fichero2");
		} catch (IOException ioe) {ioe.printStackTrace();}
		try{
			File f3=new File("NuevoDirectorio/Fichero3.txt");//objeto f3 de la clase file
			f3.createNewFile(); //crea el Fichero3.txt en el nuevo directorio
		}catch (IOException ioe) {ioe.printStackTrace();}
		
		if(f4.delete()) //borra directorio o fichero asociado a f4; Comprobar mensaje cuando se borra un fichero que no existe
			System.out.println("Fichero4 borrado");
		else
			System.out.println("No se ha podido borrar el Fichero4");
		//Información sobre un fichero
		if(f1.exists()){//devuelve true si el fichero/directorio existe
			System.out.println("Nombre del fichero: "+f1.getName());//devuelve nombre fichero/directorio
			System.out.println("Ruta: "+f1.getPath());//devuelve camino relativo
			System.out.println("Ruta absoluta: "+f1.getAbsolutePath());//devuelve ruta completa
			System.out.println("Se puede escribir: "+f1.canRead());//true si el fichero puede leerse
			System.out.println("Se puede leer: "+f1.canWrite());//true si el fichero puede escribirse
			System.out.println("Tamaño: "+f1.length());//longitud fichero en bytes
		}
	}
}
