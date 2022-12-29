package com.app.inventario.usuario.infrastructure.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "usuarios_validar")
public class UsuarioValidar {
 
	 @Id
	 public String nombre;
	 public String usuarioId;

	 public UsuarioValidar() {
	 }

	 public UsuarioValidar(String nombre, String usuarioId) {
	      this.nombre = nombre;
	      this.usuarioId = usuarioId;
	 }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}
	 
}
