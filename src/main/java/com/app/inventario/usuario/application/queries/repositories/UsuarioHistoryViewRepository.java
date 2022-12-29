package com.app.inventario.usuario.application.queries.repositories;

import org.springframework.stereotype.Repository;

import com.app.inventario.usuario.application.queries.views.UsuarioHistoryView;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface UsuarioHistoryViewRepository extends JpaRepository<UsuarioHistoryView, String> {
  
	 @Query(value = "SELECT * FROM usuarios_history WHERE usuario_id = (SELECT MAX(usuario_id) FROM usuarios_history WHERE usuario_id = :usuarioId)", nativeQuery = true)
	 Optional<UsuarioHistoryView> getLastByUsuarioId(String usuarioId);

	 @Query(value = "SELECT * FROM usuarios_history WHERE usuario_id = :usuarioId ORDER BY created_at", nativeQuery = true)
	 List<UsuarioHistoryView> getHistoryByUsuarioId(String usuarioId);
}
