package clases;
/*Departamentos.java es el JavaBean que facilita el intercambio
 * de datos entre el controlador y el modelo y posteriormente
 * entre controlador y la vista
 */



public class Departamentos {
	private Byte dept_no;
	private String dnombre;
	private String loc;
	
	public Departamentos(){
		
	}
	public Departamentos(Byte dept, final String dnombre, final String loc){
		this.dept_no= dept;
		this.dnombre=dnombre;
		this.loc=loc;
	}
	public Byte getDeptno(){
		return this.dept_no;
	}
	public void setDeptno(Byte dept){
		this.dept_no=dept;
	}
	public String getDnombre(){
		return this.dnombre;
	}
	public void setDnombre(String dnombre){
		this.dnombre=dnombre;
	}
	public String getLoc(){
		return this.loc;
	}
	public void setLoc(String loc){
		this.loc=loc;
	}
}

