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
import javax.servlet.http.HttpSession;

import es.curso.controllers.ActualizarController;
import es.curso.controllers.EliminarController;
import es.curso.controllers.LoginController;
import es.curso.controllers.ejb.ActualizarControllerEjb;
import es.curso.controllers.ejb.BuscarPorNombreControllerEjb;
import es.curso.controllers.ejb.DarAltaClienteControllerEjb;
import es.curso.controllers.ejb.EliminarControllerEjb;
import es.curso.controllers.ejb.ListarTodosControllerEjb;
import es.curso.controllers.ejb.LoginControllerEjb;
import es.curso.model.entity.Cliente;
import es.curso.model.entity.Usuario;

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
		 HttpSession miSession = request.getSession();
		 // preguntar si la peticioon es login
		 if(action.equals("login")){
			 // invalidar la sesion
			 miSession.invalidate();
			 // redirigir a login
			 rd= request.getRequestDispatcher("/login.jsp");
             rd.forward(request, response); 
        }
		 else{
			 // las otras opciones 
			 //preguntar si la sesion está activa
			     // hacer el switch
			 if(miSession.getAttribute("userName")!=null){
				 switch(action){
				     case "altaCliente": // se debe redirigir hacia el formulario altaCliente
				    	 
				    	                  rd= request.getRequestDispatcher("/jsp/altaCliente.jsp");
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
				     case "buscarPorNombre": // se redirigirá hacia 
				    	                     // el formulario buscar por nombre
				    	                    
				    	                   rd = request.getRequestDispatcher("/jsp/buscarPorNombre.jsp");
				    	                   rd.forward(request, response);
				    	                   break;  
				     case "eliminarPorId":
				    	                   rd= request.getRequestDispatcher("/jsp/eliminarPorId.jsp");
				    	                   rd.forward(request, response);
				    	                   break;
				     case "logout":		   
				    	                 miSession.invalidate();
				    	                 miSession= request.getSession(false);
//				    	                 rd= request.getRequestDispatcher("/login.jsp");
//							             rd.forward(request, response); 
					    	   
				    	                 response.sendRedirect("login");
							             break;
				      	                   
				   }
				  // No HAY NADA....
		      }// cierre del if q verifica la sesion
			 else{
				 response.sendRedirect("login");
			 }
		 }
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo().substring(1);
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd;
		
		if(request.getSession().getAttribute("userName")!=null){
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
				                 rd = request.getRequestDispatcher("/index.jsp");
				        		 rd.forward(request, response);
				                break;
			case "buscarPorNombre":// recuperar la cadena tecleada en el formulario
				                   String cadenaNombre= request.getParameter("nombre");
				                   // llamar al controlador adecuado
				                   BuscarPorNombreControllerEjb controladorBusqueda= new BuscarPorNombreControllerEjb();
				                   ArrayList<Cliente> resultado =controladorBusqueda.buscarPorNombre(cadenaNombre);
				                   // meter en el request el arraylist de respuesta
				                   request.setAttribute("clientes", resultado);
				                  // mandarle un título diferente
				                    request.setAttribute("titulo","Búsqueda por " + cadenaNombre);
				                   // y redirigir hacia el jsp ListarTodos
				                   rd=request.getRequestDispatcher("/jsp/listarTodos.jsp");
				                   rd.forward(request, response);
				                  
				                  break;
			case "eliminarPorId":
				                  // recuperar el id tecleado en el el form
				                  int id = Integer.parseInt(request.getParameter("id"));
				                  // llamar al controlador
				                  EliminarController eliminarEjb = new EliminarControllerEjb();
				                  eliminarEjb.eliminar(id);
	                              response.sendRedirect("/Ej_15GitHub/Tienda/listarTodos");
				                  break;
			case "actualizar":   // recuperar los datos del formulario 
				                 int idCliente = Integer.parseInt(request.getParameter("id")); 
				                 String nombresCliente = request.getParameter("nombres");
				                 String apellidosCliente = request.getParameter("apellidos");
				                 String dniCliente= request.getParameter("dni");
				                 Cliente clienteModif = 
				                		 new Cliente(idCliente, nombresCliente, apellidosCliente, dniCliente);
				                 ActualizarController actualizarEjb = new ActualizarControllerEjb();
				                 actualizarEjb.actualizar(clienteModif);
				                 response.sendRedirect("/Ej_15GitHub/Tienda/listarTodos");
				                 
				                 break;
		         
							                 
			   }// fin del switch
		} // fin del if (parte verdadera)
		else{
		   if(action.equals("login")){
			   // recuperar los datos del formulario
			     HttpSession session = request.getSession(false);
          	     session.invalidate();
                 String userName = request.getParameter("userName");
                 String password = request.getParameter("password");
                 // invocra la controlador adecuador
                 LoginController loginController = new LoginControllerEjb();
                 Usuario usuario = loginController.login(userName,password);
                 if(usuario!=null){
                	 //   Si el usuario existe... meter los datos de ese usuario en la sesion
                	 
                	 session = request.getSession(true);
                	 session.setMaxInactiveInterval(30);
                   	// aqui tengo q rellenar los datos del usuario
                	 String nombreCompleto = usuario.getNombres() + " " + usuario.getApellidos();
                	 session.setAttribute("nombreCompleto", nombreCompleto);
                	 session.setAttribute("userName", usuario.getUserName());
                	 rd = request.getRequestDispatcher("/index.jsp");
	        		 rd.forward(request, response);
                 }else{
                	 // si no existe redirigir hacía login otra vez 
                	 response.sendRedirect("login");
	                 }
		   }
	}
  }
}













































