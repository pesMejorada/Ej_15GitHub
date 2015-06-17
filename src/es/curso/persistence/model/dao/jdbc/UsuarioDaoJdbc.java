package es.curso.persistence.model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.curso.model.entity.Usuario;
import es.curso.persistence.model.dao.UsuarioDao;

public class UsuarioDaoJdbc implements UsuarioDao {
     
	private Connection cx;

	@Override
	public Usuario searchForUserNamePassword(String userName, String password) {
		
		Usuario usuario =null;
		try {
			// abrir la conexion
			abrirConexion();
			//2. prepara la sentencia
			PreparedStatement ps = 
					 cx.prepareStatement(
							 "SELECT * FROM USUARIO WHERE userName=? AND password=?");
			// 2.1  rellenar los interrogantes
			  ps.setString(1, userName);
			  ps.setString(2, password);
			// 3. ejecutar la sentencia  
			  ResultSet consulta = ps.executeQuery();
			  
              if(consulta.next()){
            	  // traspasar los datos del resultset al usuario
            	  usuario = new Usuario();
            	  usuario.setId(consulta.getInt("id"));
            	  usuario.setNombres(consulta.getString("nombres"));
            	  usuario.setApellidos(consulta.getString("apellidos"));
            	  usuario.setUserName(consulta.getString("userName"));
            	  usuario.setPassword(consulta.getString("password"));
              }  			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  finally{
			  
			  cerrarConexion();
	  }
	return usuario;
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
			//3. iniciar el autoCommit en false para gestionar TRANSACCIONES
			cx.setAutoCommit(false);
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
}
