import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;


public class Departamento implements Serializable{
	private int numero;
	private String nombre;
	private String localidad;
	
	
	public Departamento(int n, String no, String l){
		numero = n;
		nombre = no;
		localidad = l;
	}
	
	public Departamento() {
		// TODO Auto-generated constructor stub
	}

	int getNumero() {
		return numero;
	}
	void setNumero(int numero) {
		this.numero = numero;
	}
	String getNombre() {
		return nombre;
	}
	void setNombre(String nombre) {
		this.nombre = nombre;
	}
	String getLocalidad() {
		return localidad;
	}
	void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
	void leer(RandomAccessFile raf) throws IOException {
	    numero = raf.readInt();
		char[] temp = new char[20];
	    for (int i = 0; i < temp.length; i++)
	      temp[i] = raf.readChar();
	    nombre = new String(temp);
	    temp = new char[50];
	    for (int i = 0; i < temp.length; i++)
	      temp[i] = raf.readChar();
	    localidad = new String(temp);
	}
	
	void write(RandomAccessFile raf) throws IOException {
	    
		raf.writeInt(numero);
		StringBuffer sb;
	    if (nombre != null)
	      sb = new StringBuffer(nombre);
	    else
	      sb = new StringBuffer();
	    sb.setLength(20);
	    raf.writeChars(sb.toString());

	    if (localidad != null)
	      sb = new StringBuffer(localidad);
	    else
	      sb = new StringBuffer();
	    sb.setLength(50);
	    raf.writeChars(sb.toString());

	  }
	
	int tamano() {
	    return 2 * (20 + 50) + 4;
	  }
}
