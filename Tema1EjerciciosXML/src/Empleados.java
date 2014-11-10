import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

public class Empleados implements Serializable {
	private String dni;// atributo del objeto
	private String nombre;// atributo del objeto
	private String apellido;// atributo del objeto
	private float base;// atributo del objeto
	private float comp;// atributo del objeto
	private float irpf;// atributo del objeto

	public Empleados(String dni, String nombre, String apellido, float base,
			float comp, float irpf) {
		this.nombre = nombre;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.base = base;
		this.comp = comp;
		this.irpf = irpf;
	}

	public Empleados(){
		
	}
	public void setDni(String Dni) {
		dni = Dni;
	}

	public void setNombre(String nom) {
		nombre = nom;
	}

	public void setApellido(String Apellido) {
		apellido = Apellido;
	}

	public void setBase(float Base) {
		base = Base;
	}

	public void setComplemento(float Comp) {
		comp = Comp;
	}

	public void setIRPF(float Irpf) {
		irpf = Irpf;
	}

	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public float getBase() {
		return base;
	}

	public float getComp() {
		return comp;
	}

	public float getIrpf() {
		return irpf;
	}

	void leer(RandomAccessFile raf) throws IOException {
		// dni = raf.readLine();
		char aux;
		char[] temp = new char[10];
		for (int i = 0; i < temp.length; i++){
			aux= raf.readChar();
			temp[i]=aux;
		}
		dni = new String(temp);//
		
		temp = new char[20];
		for (int i = 0; i < temp.length; i++){
			temp[i] = raf.readChar();
		}
		nombre = new String(temp);//

		temp = new char[20];
		for (int i = 0; i < temp.length; i++){
			temp[i] = raf.readChar();
		}
		apellido = new String(temp);

		base = raf.readFloat();
		comp = raf.readFloat();
		irpf = raf.readFloat();

	}

	void write(RandomAccessFile raf) throws IOException {

		// raf.writeInt(numeroDepartamento);
		StringBuffer sb;

		if (dni != null)
			sb = new StringBuffer(dni);
		else
			sb = new StringBuffer();
		sb.setLength(10);
		raf.writeChars(sb.toString());

		if (nombre != null)
			sb = new StringBuffer(nombre);
		else
			sb = new StringBuffer();
		sb.setLength(20);
		raf.writeChars(sb.toString());

		if (apellido != null)
			sb = new StringBuffer(apellido);
		else
			sb = new StringBuffer();
		sb.setLength(20);
		raf.writeChars(sb.toString());

		raf.writeFloat(base);
		raf.writeFloat(comp);
		raf.writeFloat(irpf);

	}

	int tamano() {
		return 2 * (10 + 20 + 20) + (3 * 4);
	}

}
