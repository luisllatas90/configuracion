package com.app.inventario.usuario.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class EditUsuarioRequest {
    private @Setter @Getter String idUsuario;
	private @Setter @Getter String nombre;
	private @Setter @Getter String clave;
	
	
}
