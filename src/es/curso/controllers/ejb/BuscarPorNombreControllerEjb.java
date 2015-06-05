package es.curso.controllers.ejb;

import java.util.ArrayList;

import es.curso.controllers.BuscarPorNombreController;
import es.curso.model.entity.Cliente;
import es.curso.persistence.model.dao.ClienteDao;
import es.curso.persistence.model.dao.jdbc.ClienteDaoJdbc;

public class BuscarPorNombreControllerEjb implements BuscarPorNombreController{
     
	@Override
	public ArrayList<Cliente> buscarPorNombre(String cadenaNombre) {
		 ClienteDao clienteDao = new ClienteDaoJdbc();
	  return clienteDao.searchByName(cadenaNombre);
			
	}

}
