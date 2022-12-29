package com.app.inventario.usuario.application.queries.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.inventario.usuario.application.queries.views.UsuarioView;

@Repository
public interface UsuarioViewRepository extends JpaRepository<UsuarioView, String> {
	 Optional<UsuarioView> getByNombre(String nombre);

	 @Query(value = "SELECT * FROM usuarios WHERE id <> :id AND nombre = :nombre", nativeQuery = true)
	 Optional<UsuarioView> getByIdAndNombre(String id, String nombre);
}
