package com.app.inventario.usuario.application.queries.projections;

import java.util.Optional;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.app.inventario.message.events.UsuarioEdited;
import com.app.inventario.message.events.UsuarioRegistered;
import com.app.inventario.usuario.application.queries.repositories.UsuarioHistoryViewRepository;
import com.app.inventario.usuario.application.queries.views.UsuarioHistoryView;

@Component
public class UsuarioHistoryViewProjection {
	private final UsuarioHistoryViewRepository usuarioHistoryViewRepository;

    public UsuarioHistoryViewProjection(UsuarioHistoryViewRepository usuarioHistoryViewRepository) {
        this.usuarioHistoryViewRepository = usuarioHistoryViewRepository;
    }

    @EventHandler
    public void on(UsuarioRegistered event) {
    	UsuarioHistoryView view = new UsuarioHistoryView(event.getId(), event.getNombre(), event.getClave(), event.getCreatedAt());
        usuarioHistoryViewRepository.save(view);
    }

    @EventHandler
    public void on(UsuarioEdited event) {
        Optional<UsuarioHistoryView> viewOptional = usuarioHistoryViewRepository.getLastByUsuarioId(event.getId());
        if (viewOptional.isPresent()) {
            UsuarioHistoryView view = viewOptional.get();
            view = new UsuarioHistoryView(view);
            view.setNombre(event.getNombre());
            view.setClave(event.getClave());
            view.setCreatedAt(event.getUpdatedAt());
            usuarioHistoryViewRepository.save(view);
        }
    }
}
