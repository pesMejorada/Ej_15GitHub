package es.curso.persistence.model.dao.jdbc;

import java.util.ArrayList;

import es.curso.model.entity.Cliente;
import es.curso.persistence.model.dao.ClienteDao;

public class ClienteDaoJdbc implements ClienteDao{
     private ArrayList<Cliente> clientes;
     
     
     
	public ClienteDaoJdbc() {
		super();
		clientes = new ArrayList<Cliente>();
	}

	@Override
	public void create(Cliente cliente) {
		// van las instrucciones 
		// para 
		//1. conectar con la base de datos
		//2. preparar la sentencia -sql- para agregar
		//3. Ejecutar la sentencia -sql-
		//4. Cerrar la conexión
		clientes.add(cliente);
		
	}

	@Override
	public ArrayList<Cliente> findAll() {
		
		clientes.add(new Cliente(1, "José", "Pérez", "1234D"));
		clientes.add(new Cliente(2, "María", "Cáceres", "65657E"));
		clientes.add(new Cliente(3, "Rosa", "Martínez", "888H"));
		clientes.add(new Cliente(4, "Rafael", "Rodríguez", "888H"));
		clientes.add(new Cliente(5, "Almudena", "García", "76767G"));
		clientes.add(new Cliente(6, "Oscar", "Peñalver", "7373K"));
		clientes.add(new Cliente(7, "Teresa", "Jimenez", "7373R"));
	return clientes;
	}
    
}











