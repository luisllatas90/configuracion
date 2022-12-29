package com.app.inventario.usuario.application.dto.response;

import lombok.Value;

@Value
public class EditUsuarioResponse {
  private String idUsuario;
  private String nombre;
  private String clave;
  
}
