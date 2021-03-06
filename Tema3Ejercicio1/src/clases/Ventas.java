package clases;

// Generated 26-dic-2014 9:42:29 by Hibernate Tools 3.4.0.CR1

/**
 * Ventas generated by hbm2java
 */
public class Ventas implements java.io.Serializable {
	private static final long serialVersionUID=1L;
	private int idventa;
	private Productos productos;
	private Clientes clientes;
	private String fechaventa;
	private Integer cantidad;

	public Ventas() {
	}

	public Ventas(int idventa) {
		this.idventa = idventa;
	}

	public Ventas(int idventa, Productos productos, Clientes clientes,
			String fechaventa, Integer cantidad) {
		this.idventa = idventa;
		this.productos = productos;
		this.clientes = clientes;
		this.fechaventa = fechaventa;
		this.cantidad = cantidad;
	}

	public int getIdventa() {
		return this.idventa;
	}

	public void setIdventa(int idventa) {
		this.idventa = idventa;
	}

	public Productos getProductos() {
		return this.productos;
	}

	public void setProductos(Productos productos) {
		this.productos = productos;
	}

	public Clientes getClientes() {
		return this.clientes;
	}

	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}

	public String getFechaventa() {
		return this.fechaventa;
	}

	public void setFechaventa(String fechaventa) {
		this.fechaventa = fechaventa;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

}
