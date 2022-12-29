package com.app.inventario.usuario.domain;

import java.time.LocalDateTime;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import com.app.inventario.message.commands.EditUsuario;
import com.app.inventario.message.commands.RegisterUsuario;
import com.app.inventario.message.events.UsuarioEdited;
import com.app.inventario.message.events.UsuarioRegistered;
import com.app.inventario.shared.domain.Nombre;
import com.app.inventario.shared.domain.Clave;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Usuario {
	 @AggregateIdentifier
     private String id;
     private Nombre nombre;
     private Clave clave; 
    
     public Usuario() {
     }

     @CommandHandler
     public Usuario(RegisterUsuario command) {
         LocalDateTime createdAt = LocalDateTime.now();
         UsuarioRegistered event = new UsuarioRegistered(
             command.getId(),
             command.getNombre(),
             command.getClave(),
             createdAt,
             command.getCreatedBy()
         );
         apply(event);
     }

     @CommandHandler
     public void handle(EditUsuario command) {
         LocalDateTime updatedAt = LocalDateTime.now();
         UsuarioEdited event = new UsuarioEdited(
             command.getId(),
             command.getNombre(),
             command.getClave(),
             updatedAt,
             command.getUpdatedBy()
         );
         apply(event);
     }

     @EventSourcingHandler
     protected void on(UsuarioRegistered event) {
         id = event.getId();
         nombre = Nombre.create(event.getNombre()).getSuccess();
         clave = Clave.create(event.getClave()).getSuccess();
     }

     @EventSourcingHandler
     protected void on(UsuarioEdited event) {
         nombre = Nombre.create(event.getNombre()).getSuccess();
         clave = Clave.create(event.getClave()).getSuccess();
     }
}
