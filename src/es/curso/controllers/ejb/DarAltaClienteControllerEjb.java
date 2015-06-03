package es.curso.controllers.ejb;

import es.curso.controllers.DarAltaClienteController;
import es.curso.model.entity.Cliente;
import es.curso.persistence.model.dao.ClienteDao;
import es.curso.persistence.model.dao.jdbc.ClienteDaoJdbc;

public class DarAltaClienteControllerEjb implements DarAltaClienteController {
    private ClienteDao clienteDao;
	@Override
	public void agregar(Cliente cliente) {
		// Logica del negocio para agregar un cliente
		// 1. Verifcar datos en Hacienda
		// 2. Agragarlo ---> llamar a la capa DAO-- para q se de de alta
		// 3. Enviar email al jefe de Obra
		// 4. enviar un email al cliente.
		clienteDao= new ClienteDaoJdbc();
		clienteDao.create(cliente);
	}

}
