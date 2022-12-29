package com.app.inventario.usuario.application.queries.projections;

import java.util.Optional;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.app.inventario.message.events.UsuarioEdited;
import com.app.inventario.message.events.UsuarioRegistered;
import com.app.inventario.usuario.application.queries.repositories.UsuarioViewRepository;
import com.app.inventario.usuario.application.queries.views.UsuarioView;

@Component
public class UsuarioViewProjection {
	private final UsuarioViewRepository usuarioViewRepository;

    public UsuarioViewProjection(UsuarioViewRepository usuarioViewRepository) {
        this.usuarioViewRepository = usuarioViewRepository;
    }

    @EventHandler
    public void on(UsuarioRegistered event) {
        UsuarioView view = new UsuarioView(event.getId(), event.getNombre(), event.getClave(), event.getCreatedAt());
        usuarioViewRepository.save(view);
    }

    @EventHandler
    public void on(UsuarioEdited event) {
        Optional<UsuarioView> viewOptional = usuarioViewRepository.findById(event.getId());
        if (viewOptional.isPresent()) {
            UsuarioView view = viewOptional.get();
            view.setNombre(event.getNombre());
            view.setClave(event.getClave());
            view.setUpdatedAt(event.getUpdatedAt());
            usuarioViewRepository.save(view);
        }
    }
}
