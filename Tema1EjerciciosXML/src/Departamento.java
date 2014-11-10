import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

public class Departamento implements Serializable {
	private int numeroDepartamento;// atributo del objeto
	private String nombre;// atributo del objeto
	private String localidad;// atributo del objeto

	public Departamento(int numeroDepartamento, String nombre, String localidad) {
		this.nombre = nombre;
		this.numeroDepartamento = numeroDepartamento;
		this.localidad = localidad;
	}
	public Departamento() {
		// TODO Auto-generated constructor stub
	}

	public void setNumeroDepartamento(int numeroDep) {
		numeroDepartamento = numeroDep;
	}

	public void setNombre(String nom) {
		nombre = nom;
	}

	public void setLocalidad(String loca) {
		localidad = loca;
	}

	public int getNumeroDepartamento() {
		return numeroDepartamento;
	}

	public String getNombre() {
		return nombre;
	}

	public String getLocalidad() {
		return localidad;
	}

	void leer(RandomAccessFile raf) throws IOException {
		numeroDepartamento = raf.readInt();
		char[] temp = new char[10];
		for (int i = 0; i < temp.length; i++)
			temp[i] = raf.readChar();
		nombre = new String(temp);//

		temp = new char[20];
		for (int i = 0; i < temp.length; i++)
			temp[i] = raf.readChar();
		localidad = new String(temp);

	}

	void write(RandomAccessFile raf) throws IOException {

		raf.writeInt(numeroDepartamento);
		StringBuffer sb;
		if (nombre != null)
			sb = new StringBuffer(nombre);
		else
			sb = new StringBuffer();
		sb.setLength(10);
		raf.writeChars(sb.toString());

		if (localidad != null)
			sb = new StringBuffer(localidad);
		else
			sb = new StringBuffer();
		sb.setLength(20);
		raf.writeChars(sb.toString());

	}

	int tamano() {
		return 2 * (10 + 20) + 4;
	}

}
