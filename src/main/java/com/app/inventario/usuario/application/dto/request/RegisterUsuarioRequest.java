package com.app.inventario.usuario.application.dto.request;

import lombok.Value;

@Value
public class RegisterUsuarioRequest {
	private String nombre;
	private String clave; 
}
