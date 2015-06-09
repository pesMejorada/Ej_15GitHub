package es.curso.persistence.model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.curso.model.entity.Cliente;
import es.curso.persistence.model.dao.ClienteDao;


public class ClienteDaoJdbc implements ClienteDao{
   private Connection cx;
   public ClienteDaoJdbc() {
		super();
	}
	@Override
	public void create(Cliente cliente) {
		try {
			// van las instrucciones 
			// para 
			//1. conectar con la base de datos
			abrirConexion();
			//2. preparar la sentencia -sql- para agregar
			PreparedStatement ps = 
			 cx.prepareStatement("INSERT INTO CLIENTE VALUES(?,?,?,?)");
			  // 2.1 insertar los datos del cliente en los ?
			  ps.setInt(1, 0);
			  ps.setString(2, cliente.getNombres());
			  ps.setString(3, cliente.getApellidos());
			  ps.setString(4, cliente.getDni());
			//3. Ejecutar la sentencia -sql-
			  ps.executeUpdate();  // = q dar al play en heidi
			        // nota: se usae executeUpdate() para las instrucciones sql
			        // como: insert delete update 
			        // Esta instrución devuelve como resultado el número de
			        // registros (o filas) afectadas.
			  // 3.1 hacer le commit... 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			//4. Cerrar la conexión
			cerrarConexion();
		}
		
	}

	@Override
	public ArrayList<Cliente> findAll() {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		   try {
			//1. abrir la conexión
			     abrirConexion();
			   // 2. preparar las sentencias
			     PreparedStatement ps= cx.prepareStatement("SELECT * FROM CLIENTE");
			   // 3. ejecutar la sentencia...
			     ResultSet consulta =ps.executeQuery();
			     // 3.1 traspasar los datos de la respuesta al arrayList
			     while(consulta.next()){
			    	 Cliente clienteTemporal = new Cliente(); 
			    	 // codigo para traspasar de la consulta(ResultSEt)
			    	 // hacia clienteTemporal
			    	 clienteTemporal.setId(consulta.getInt("id"));// lo q está entre comillas
			    	                                              //corresponde al nombre
			    	                                              //del atributo en la base de datos
			    	 clienteTemporal.setNombres(consulta.getString("nombres"));
			    	 clienteTemporal.setApellidos(consulta.getString("apellidos"));
			    	 clienteTemporal.setDni(consulta.getString("dni"));
			        clientes.add(clienteTemporal);
			     }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		   // 4. cerrar la conexion
			cerrarConexion();
		}
	return clientes;
	}
	
	private  void abrirConexion(){
			try {
				//1. determinar y validar si tengo el driver o conector (de mysql)
				Class.forName("com.mysql.jdbc.Driver");
				//2. Establecer la conexión..
				cx= DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/Tienda",
						"rootTienda",
						"rootTienda"
						);
				//3. iniciar el autoCommit en false
				//cx.setAutoCommit(false);
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	}
	
	private void cerrarConexion(){
		try {
			if(cx!=null)
			   cx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	@Override
	public ArrayList<Cliente> searchByName(String name) {
		ArrayList<Cliente> clientes= new ArrayList<Cliente>();
		try {
			// 1. Establecer la conexión con la bbdd
			     abrirConexion();
			// 2. Preparar la sentencia sql ,. paramétrizada (son las q tienen ?)
			     PreparedStatement ps = cx.prepareStatement("SELECT * FROM CLIENTE WHERE NOMBRES LIKE ?");
			  // 2.1 Especificar lo que va en ?
			     ps.setString(1, "%"+name +"%");
			// 3. Ejecutar la query
			     ResultSet resultado = ps.executeQuery();
			// 3.1 Pasar los datos que vienen de la bbdd (ResultSet) hacia
			    // el ArrayList<Cliente>
			     while(resultado.next()){
			    	 Cliente c = new Cliente();
				    	 c.setId(resultado.getInt("id"));
				    	 c.setNombres(resultado.getString("nombres"));
				    	 c.setApellidos(resultado.getString("apellidos"));
				    	 c.setDni(resultado.getNString("dni"));
			    	 clientes.add(c);
			     }
	    
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			// 4.cerrar la conexión (en el finally)
              cerrarConexion();			
		}
		return clientes;
	}
	@Override
	public void update(Cliente cliente) {
		try {
			// 1. establecer la conexion
			abrirConexion();
			// 2. preparar la sentnecia
			PreparedStatement ps = cx.prepareStatement(
				   "UPDATE CLIENTE SET NOMBRES=?, APELLIDOS=?, DNI= ? WHERE ID =?");
		    // 2.1. RELLENAR LOS ?
				ps.setString(1, cliente.getNombres());
				ps.setString(2, cliente.getApellidos());
				ps.setString(3, cliente.getDni());
				ps.setInt(4, cliente.getId());
		   // 3. Ejecutar la sentencia
				ps.executeUpdate();
		   		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			//4.cerrar conexion
			cerrarConexion();
		}
	}
	@Override
	public void delete(Integer id) {
		try {
			//.establecer conexioi
			abrirConexion();
			//2. preparar las sentencias
			PreparedStatement ps= cx.prepareStatement("DELETE FROM CLIENTE WHERE ID =?");
			//2.1. especificar loq  va en ?
			ps.setInt(1, id);
			//3. eJECUTAR LA SENTENCIA
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			 // 4. cerrar la conexio
			cerrarConexion();
		}
		
		
	}
    
}







































