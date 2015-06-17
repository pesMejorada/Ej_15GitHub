package es.curso.controllers;

import es.curso.model.entity.Usuario;

public interface LoginController {

	Usuario login(String userName, String password);

}
