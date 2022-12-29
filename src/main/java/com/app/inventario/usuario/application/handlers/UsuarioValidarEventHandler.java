package com.app.inventario.usuario.application.handlers;

import java.util.Optional;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.app.inventario.message.events.UsuarioEdited;
import com.app.inventario.message.events.UsuarioRegistered;
import com.app.inventario.usuario.infrastructure.entities.UsuarioValidar;
import com.app.inventario.usuario.infrastructure.repositories.UsuarioValidarRepository;

@Component
@ProcessingGroup("usuarioValidar")
public class UsuarioValidarEventHandler {
	private final UsuarioValidarRepository usuarioValidarRepository;
	
	public UsuarioValidarEventHandler(UsuarioValidarRepository usuarioValidarRepository) {
        this.usuarioValidarRepository = usuarioValidarRepository;
    }

    @EventHandler
    public void on(UsuarioRegistered event) {
    	usuarioValidarRepository.save(new UsuarioValidar(event.getNombre(), event.getId()));
    }

    @EventHandler
    public void on(UsuarioEdited event) {
        Optional<UsuarioValidar> clientDniOptional = usuarioValidarRepository.getByUsuarioId(event.getId());
        clientDniOptional.ifPresent(usuarioValidarRepository::delete);
        usuarioValidarRepository.save(new UsuarioValidar(event.getNombre(), event.getId()));
    }
	
}
