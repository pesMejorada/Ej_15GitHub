package es.curso.dispatchers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import es.curso.controllers.ejb.DarAltaClienteControllerEjb;
import es.curso.model.entity.Cliente;

/**
 * Servlet implementation class TiendaServlet
 */
@WebServlet("/Tienda/*")
public class TiendaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TiendaServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String action = request.getPathInfo().substring(1);
		 request.setCharacterEncoding("UTF-8");
		 switch(action){
		     case  "listarTodos":  // se invocará al controllador adecuado
		    	                   // q obtendrá todos los clientes
		    	                  //esta peticon redirije a otra pagina.
		    	                  break;
		     case "buscarPorNombre": // se invocará al controlador que haga
		    	                     // la consulta por nombre, que obtendrá
		    	                     // solo los clientes que coincidan con el nombre buscado
		    	                     // esta peticion redirije a otra pagina
		    	                   break;  	                  
		 }
		  // tengo q redirigir hacia una vista jsp para mostrar los clientes
		   
		 RequestDispatcher  rd; 
		 // de laguna manera haya q enviarle a la vista el resultado de la consulta a la
		  // base de datos.... 
		 //this.getServletContext().getRequestDispatcher("/jsp" +"listarTodos.jsp").forward(request, response);;
		 rd = request.getRequestDispatcher("/jsp/listarTodos.jsp");
		 rd.forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo().substring(1);
		request.setCharacterEncoding("UTF-8");
		switch(action){
		case "altaCliente":
			                 // recuperar los datos tecleados en el formulario
			                 String nombres = request.getParameter("nombres");
			                 String apellidos = request.getParameter("apellidos");
			                 String dni = request.getParameter("dni");
			                 Cliente cliente = new Cliente(0, nombres, apellidos, dni);
			                 // invocará al controlador adecuado
			                 DarAltaClienteControllerEjb controlador= new DarAltaClienteControllerEjb();
			                 controlador.agregar(cliente);
			                break;
			}
	}

}
















