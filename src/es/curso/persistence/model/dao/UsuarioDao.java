package es.curso.persistence.model.dao;

import es.curso.model.entity.Usuario;

public interface UsuarioDao {

	Usuario searchForUserNamePassword(String userName, String password);

}
