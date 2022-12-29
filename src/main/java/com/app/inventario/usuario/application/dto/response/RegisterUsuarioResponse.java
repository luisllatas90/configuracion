package com.app.inventario.usuario.application.dto.response;

import lombok.Value;

@Value
public class RegisterUsuarioResponse {
	private String idUsuario;
	private String nombre;
	private String clave; 
}
