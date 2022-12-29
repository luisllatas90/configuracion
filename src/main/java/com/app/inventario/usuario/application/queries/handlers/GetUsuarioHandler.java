package com.app.inventario.usuario.application.queries.handlers;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.app.inventario.message.queries.GetAll;
import com.app.inventario.message.queries.GetHistoryByUsuarioId;
import com.app.inventario.usuario.application.queries.repositories.UsuarioHistoryViewRepository;
import com.app.inventario.usuario.application.queries.repositories.UsuarioViewRepository;
import com.app.inventario.usuario.application.queries.views.UsuarioHistoryView;
import com.app.inventario.usuario.application.queries.views.UsuarioView;

@Component
public class GetUsuarioHandler {
	private final UsuarioViewRepository usuarioViewRepository;
    private final UsuarioHistoryViewRepository usuarioHistoryViewRepository;

    public GetUsuarioHandler(UsuarioViewRepository usuarioViewRepository, UsuarioHistoryViewRepository usuarioHistoryViewRepository) {
        this.usuarioViewRepository = usuarioViewRepository;
        this.usuarioHistoryViewRepository = usuarioHistoryViewRepository;
    }

    @QueryHandler
    public List<UsuarioView> handle(GetAll query) {
        return this.usuarioViewRepository.findAll();
    }

    @QueryHandler
    public List<UsuarioHistoryView> handle(GetHistoryByUsuarioId query) {
        return this.usuarioHistoryViewRepository.getHistoryByUsuarioId(query.getUsuarioId());
    }
}
