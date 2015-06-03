package es.curso.dispatchers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import es.curso.controllers.ejb.DarAltaClienteControllerEjb;
import es.curso.controllers.ejb.ListarTodosControllerEjb;
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
		 String titulo = "Sin título";
		 RequestDispatcher  rd; 
		 switch(action){
		      case "altaCliente":
						    	  rd = request.getRequestDispatcher("/archivosHtml/altaClienteView.html");
				                  rd.forward(request, response);
		    	                  break;
		     case  "listarTodos":  // se invocará al controllador adecuado
		    	                   // q obtendrá todos los clientes
		    	                  //esta peticon redirije a otra pagina.
		    	                   ListarTodosControllerEjb todos= new ListarTodosControllerEjb();
		    	                   ArrayList<Cliente> clientes=todos.listarTodos();
		    	                   request.setAttribute("clientes", clientes);
		    	                   titulo="Listado general de clientes";
		    	                   request.setAttribute("titulo", titulo);
		    	                   rd = request.getRequestDispatcher("/jsp/listarTodos.jsp");
		    	                   rd.forward(request, response);
		    	                  break;
		     case "buscarPorNombre": // se invocará al controlador que haga
		    	                     // la consulta por nombre, que obtendrá
		    	                     // solo los clientes que coincidan con el nombre buscado
		    	                     // esta peticion redirije a otra pagina
		    	                   titulo="Resultado de la búsqueda por nombre";
		    	                   request.setAttribute("titulo", titulo);
		    	                   rd = request.getRequestDispatcher("/jsp/buscarPorNombre.jsp");
		    	                   rd.forward(request, response);
		    	                   break;  	                  
		 }
		  // No HAY NADA....
		 
		    
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo().substring(1);
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd;
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
			                 rd = request.getRequestDispatcher("/index.html");
			        		 rd.forward(request, response);
			                break;
			}
	}

}
















