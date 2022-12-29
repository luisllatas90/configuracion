package com.app.inventario.usuario.application.validators;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.app.inventario.message.commands.RegisterUsuario;
import com.app.inventario.shared.application.Notification;
import com.app.inventario.usuario.infrastructure.entities.UsuarioValidar;
import com.app.inventario.usuario.infrastructure.repositories.UsuarioValidarRepository;

@Component
public class RegisterUsuarioValidator {
	private final UsuarioValidarRepository usuarioValidarRepository;
	
	public RegisterUsuarioValidator(UsuarioValidarRepository usuarioValidarRepository) {
		this.usuarioValidarRepository = usuarioValidarRepository;
    }
	
	public Notification validate(RegisterUsuario command)
    {
        Notification notification = new Notification();
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
        
        Optional<UsuarioValidar> optional = usuarioValidarRepository.findById(nombre);
        if (optional.isPresent()) notification.addError("Nombre de usuario ya se encuentra registrado");

        return notification;
    }
}
