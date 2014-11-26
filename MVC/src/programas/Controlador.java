package programas;

import programas.OperacionesBD;
import clases.Departamentos;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controlador
 */
@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Controlador() {
        // TODO Auto-generated constructor stub
    }

    public  void service (HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
			String op=request.getParameter("accion");
			//Si se ha pulsado en alta de departamento se visualiza la pantalla de alta
			if (op.equals("alta")) response.sendRedirect("Alta.jsp");
			
			/*Si se ha pulsado en listado, primero se cargan los datos de departamento
			* en una lista y luego se envíana a lista.jsp*/
			if (op.equals("listado")) {
				OperacionesBD operBD=new OperacionesBD();
				ArrayList lista=operBD.listarDep(); //cargar los datos de los dep
				request.setAttribute("departamentos",lista); // preparar para enviar al jsp
				RequestDispatcher rd=request.getRequestDispatcher("Listado.jsp");
				rd.forward(request, response);
			}
			
			//se inserta el depatamento en la tabla y luego se visualiza index.html
			if (op.equals("insertar")){
				Byte numdep=Byte.parseByte(request.getParameter("dept_no"));
				String dnom=request.getParameter("dnombre");
				String dloc=request.getParameter("loc");
				Departamentos dep=new Departamentos(numdep,dnom,dloc);
				OperacionesBD operBD= new OperacionesBD();
				operBD.insertaDepartamento(dep);
				response.sendRedirect("index.html");
			}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
