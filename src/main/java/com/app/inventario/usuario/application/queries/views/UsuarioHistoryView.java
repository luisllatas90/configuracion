package com.app.inventario.usuario.application.queries.views;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "usuarios_history")
public class UsuarioHistoryView {
	    @Id @GeneratedValue @Getter @Setter
	    private Long id;
	    @Column(length=36) @Getter @Setter
	    private String usuarioId;
	    @Column(length=50) @Getter @Setter
	    private String nombre;
	    @Column(length=24) @Getter @Setter
	    private String clave;
	    @Column() @Getter @Setter
	    private LocalDateTime createdAt;
	    @Column(nullable = true) @Getter @Setter
	    private LocalDateTime updatedAt;

	    public UsuarioHistoryView() {
	    }

	    public UsuarioHistoryView(String usuarioId, String nombre, String clave, LocalDateTime createdAt) {
	        this.usuarioId = usuarioId;
	        this.nombre = nombre;
	        this.clave = clave;
	        this.createdAt = createdAt;
	    }
	    
	    public UsuarioHistoryView(UsuarioHistoryView view) {
	    	this.usuarioId = view.getUsuarioId();
	    	this.nombre = view.getNombre();
	        this.clave = view.getClave();
	        this.createdAt = view.getCreatedAt();
	        
	    }
	    

}