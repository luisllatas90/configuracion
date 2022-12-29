package com.app.inventario.usuario.infrastructure.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.inventario.usuario.infrastructure.entities.UsuarioValidar;

public interface  UsuarioValidarRepository extends JpaRepository<UsuarioValidar, String> {
	Optional<UsuarioValidar> getByUsuarioId(String usuarioId);

    @Query(value = "SELECT * FROM usuarios_validar WHERE usuario_id <> :usuarioId AND nombre = :nombre LIMIT 1", nativeQuery = true)
    Optional<UsuarioValidar> getByNombreForDistinctUsuarioId(String nombre, String usuarioId);
}
