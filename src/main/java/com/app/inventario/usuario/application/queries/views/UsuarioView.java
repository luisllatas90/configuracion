package com.app.inventario.usuario.application.queries.views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity(name = "usuarios")
public class UsuarioView {
	@Id @Column(length=36) @Getter @Setter
    private String id;
    @Column(length=50) @Getter @Setter
    private String nombre;
    @Column(length=25) @Getter @Setter
    private String clave;
    @Column() @Getter @Setter
    private LocalDateTime createdAt;
    @Column(nullable = true) @Getter @Setter
    private LocalDateTime updatedAt;

    public UsuarioView() {
    }

    public UsuarioView(String id, String nombre, String clave, LocalDateTime createdAt) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
        this.createdAt = createdAt;
    }
	
}
