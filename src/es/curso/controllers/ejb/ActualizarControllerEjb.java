package es.curso.controllers.ejb;

import es.curso.controllers.ActualizarController;
import es.curso.model.entity.Cliente;
import es.curso.persistence.model.dao.ClienteDao;
import es.curso.persistence.model.dao.jdbc.ClienteDaoJdbc;

public class ActualizarControllerEjb implements ActualizarController{

	@Override
	public void actualizar(Cliente cliente) {
	     ClienteDao clienteDao = new ClienteDaoJdbc();
	     // 1. comprobar si está autorizado para modificar
	     clienteDao.update(cliente); 
	     // enviar email al cleinte
	     // enviar un emal a la Seg Social
	     
	}

}
