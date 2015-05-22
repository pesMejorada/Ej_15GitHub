package es.curso.controllers.ejb;

import es.curso.controllers.DarAltaClienteController;
import es.curso.model.entity.Cliente;

public class DarAltaClienteControllerEjb implements DarAltaClienteController {

	@Override
	public void agregar(Cliente cliente) {
		// Logica del negocio para agregar un cliente
		// 1. Verifcar datos en Hacienda
		// 2. Agragarlo ---> llamar a la capa DAO-- para q se de de alta
		// 3. Enviar email al jefe de Obra
		// 4. enviar un email al cliente.
		
	}

}
