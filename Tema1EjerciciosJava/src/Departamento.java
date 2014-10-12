import java.io.Serializable;

public class Departamento implements Serializable {
	private int numeroDepartamento;
	private String nombre;// atributo del objeto
	private String localidad;// atributo del objeto

	public Departamento(int numeroDepartamento, String nombre, String localidad) {
		this.nombre = nombre;
		this.numeroDepartamento = numeroDepartamento;
		this.localidad = localidad;
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
}
