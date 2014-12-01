import java.sql.*;
import java.util.Scanner;

public class ejer4 {
	public static void main(String[] args) {
		int numeroDepart,numeroEmpleados = 0;
		float salarioMedio=0;
		Scanner teclado=new Scanner(System.in);
		try {

			Class.forName("org.sqlite.JDBC").newInstance();
			
			Connection conexion = DriverManager.getConnection("jdbc:sqlite:c:/sqlite/empresas.db");
																		
			Statement sentencia = conexion.createStatement();
			
			do{
				
			System.out.print("Introduzca numero de departamento para visualizar sus empleados: ");
			numeroDepart=teclado.nextInt();
			ResultSet resulEmple=null;
			
			resulEmple = sentencia.executeQuery("select deptNo from departamentos where departamentos.deptNo='" + String.valueOf(numeroDepart)+"'");
			
			while (!resulEmple.next() && numeroDepart!=0) {//si no permite avanzar, es que no existe
				
				System.out.print("Introduzca numero de departamento de nuevo: "); 
				numeroDepart=teclado.nextInt();
				resulEmple = sentencia.executeQuery("select deptNo from departamentos where departamentos.deptNo='" + String.valueOf(numeroDepart)+"'");
			}
			//construir orden SELECT
			String sql="SELECT departamentos.nombre,apellido, salario,oficio FROM empleados,departamentos where empleados.deptNo=departamentos.deptNo ";
			sql+="and empleados.deptNo = ?";
			PreparedStatement sentencia2=conexion.prepareStatement(sql);
			sentencia2.setInt(1,(numeroDepart));
			ResultSet rs=sentencia2.executeQuery();
			while (rs.next()){
				numeroEmpleados++;
				System.out.println("Nombre departamento:" + rs.getString("nombre") + "\nApellido del empleado: " + rs.getString("apellido")+"\nSalario empleado: "+rs.getString("salario")+"\nOficio empleado: "+rs.getString("oficio"));
				System.out.println("------------------------------");
				salarioMedio=((salarioMedio+Float.parseFloat(rs.getString("salario")))/numeroEmpleados);
			}
			
			if(numeroDepart!=0){
				System.out.println("==========================================");
				System.out.println("El salario medio del departamento "+numeroDepart+" es: "+ salarioMedio+" y el  número de empleados es: "+numeroEmpleados);
			}
			System.out.print("Introduzca numero de departamento para visualizar sus empleados (0 finaliza): ");
			numeroDepart=teclado.nextInt();
		}
		while(numeroDepart!=0);
		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (InstantiationException ie) {
			ie.printStackTrace();
		} catch (IllegalAccessException ia) {
			ia.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
