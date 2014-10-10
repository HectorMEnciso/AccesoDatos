//Data_IO_Stream: ejemplo de lectura/escritura de datos tipos primitivos: int, float, longs,...
import java.io.*;
public class Data_IO_Stream {
public static void main(String[] args) throws IOException{
	
	
	File fic=new File("c:\\datos\\meses.dat");
	
	FileOutputStream ficsal=new FileOutputStream(fic);//Se crea el flujo de entrada y salida y los datos que se van a escribir
	
	DataOutputStream datsal=new DataOutputStream(ficsal);
	
	FileInputStream ficent=new FileInputStream(fic);
	
	DataInputStream datent=new DataInputStream(ficent);
	
	String meses[]={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
	
	int dias[]={31,28,31,30,31,30,31,31,30,31,30,31};
	
		for (int i = 0; i < dias.length; i++) {
			
			datsal.writeUTF(meses[i]); // escribir el mes
			
			datsal.writeInt(dias[i]); // escribir los días del mes
		}
		datsal.close(); // cerrar el flujo

		// Se procede a leer los datos del fichero
		String n;
		int d;
		for (int i = 0; i < dias.length; i++) {
			n = datent.readUTF(); // lee el mes
			d = datent.readInt(); // lee los días
			System.out.println("El mes de " + n + " tiene " + d + " días");
		}
		datent.close(); // cerrar el stream
	}
}
