package com.app.inventario.usuario.application.validators;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.app.inventario.message.commands.EditUsuario;
import com.app.inventario.shared.application.Notification;
import com.app.inventario.usuario.infrastructure.entities.UsuarioValidar;
import com.app.inventario.usuario.infrastructure.repositories.UsuarioValidarRepository;

@Component
public class EditUsuarioValidator {
	private final UsuarioValidarRepository usuarioValidarRepository;
	
	public EditUsuarioValidator(UsuarioValidarRepository usuarioValidarRepository) {
		this.usuarioValidarRepository = usuarioValidarRepository;
	}

	 public Notification validate(EditUsuario command)
	    {
	        Notification notification = new Notification();
	        String idUsuario = command.getId();
	        if (idUsuario.isEmpty()) {
	            notification.addError("ID usuario es requerido");
	        }
	        String nombre = command.getNombre().trim();
	        if (nombre.isEmpty()) {
	            notification.addError("Nombre de usuario es requerido");
	        }
	        String clave = command.getClave().trim();
	        if (clave.isEmpty()) {
	            notification.addError("Clave de usuario es requerido");
	        }
	        if (notification.hasErrors()) {
	            return notification;
	        }
	        
	        Optional<UsuarioValidar> optional = usuarioValidarRepository.getByUsuarioId(idUsuario);
	        if (optional.isEmpty()) {
	            notification.addError("Id de Usuario no encontrado");
	            return notification;
	        }
	        optional = usuarioValidarRepository.getByNombreForDistinctUsuarioId(nombre, idUsuario);
	        if (optional.isPresent()) notification.addError("Nombre de cliente ya se encuentra registrado");
	        
	        return notification;
	    }
}
