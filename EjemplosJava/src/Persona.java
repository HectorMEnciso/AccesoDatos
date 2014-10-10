/*Definimos la clase Persona, que implementa la interfaz Serializable y se utilizar� para escribir
y leer objetos en un fichero binario. La clase tiene los siguientes atributos y m�todos:
Atributos: nombre y edad
M�todos: get para obtener el valor del atributo y set para darle valor al atributo*/

import java.io.Serializable;

public class Persona implements Serializable {
	private String nombre;//atributo del objeto
	private int edad;//atributo del objeto

	public Persona(String nombre, int edad) { //Constructor de clase que adem�s de la instanciaci�n se le pasan como parametros el nombre y la edad para inicializar valores
		this.nombre = nombre;
		this.edad = edad;
	}

	public Persona() { //objeto mudo para llamar a ciertos metodos?
		this.nombre = null;
	}

	public void setNombre(String nom) {
		nombre = nom;
	}

	public void setEdad(int ed) {
		edad = ed;
	}

	public String getNombre() {
		return nombre;
	}

	public int getEdad() {
		return edad;
	}
}
