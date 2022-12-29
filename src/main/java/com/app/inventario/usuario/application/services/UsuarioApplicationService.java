package com.app.inventario.usuario.application.services;


import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;

import com.app.inventario.usuario.application.dto.request.EditUsuarioRequest;
import com.app.inventario.usuario.application.dto.request.RegisterUsuarioRequest;
import com.app.inventario.usuario.application.dto.response.EditUsuarioResponse;
import com.app.inventario.usuario.application.dto.response.RegisterUsuarioResponse;
import com.app.inventario.usuario.application.validators.EditUsuarioValidator;
import com.app.inventario.usuario.application.validators.RegisterUsuarioValidator;

import com.app.inventario.shared.application.Result;
import com.app.inventario.message.commands.EditUsuario;
import com.app.inventario.message.commands.RegisterUsuario;
import com.app.inventario.shared.application.Notification;

@Component
public class UsuarioApplicationService {
	private final CommandGateway commandGateway;
    private final RegisterUsuarioValidator registerUsuarioValidator;
    private final EditUsuarioValidator editUsuarioValidator;
    
    public UsuarioApplicationService(CommandGateway commandGateway, RegisterUsuarioValidator registerUsuarioValidator, EditUsuarioValidator editUsuarioValidator) {
        this.commandGateway = commandGateway;
        this.registerUsuarioValidator = registerUsuarioValidator;
        this.editUsuarioValidator = editUsuarioValidator;
    }
    
    public Result<RegisterUsuarioResponse, Notification> register(RegisterUsuarioRequest request) throws Exception {
        String idUsuario =  UUID.randomUUID().toString();
        String createdBy = UUID.randomUUID().toString();
        RegisterUsuario command = new RegisterUsuario(
        	idUsuario,
            request.getNombre().trim(),
            request.getClave().trim(),
            createdBy
        );
        Notification notification = this.registerUsuarioValidator.validate(command);
        if (notification.hasErrors()) return Result.failure(notification);
        commandGateway.sendAndWait(command);	
        	
        RegisterUsuarioResponse registerUsuarioResponse = new RegisterUsuarioResponse(
            command.getId(),
            command.getNombre(),
            command.getClave()
        );
        return Result.success(registerUsuarioResponse);
    }
    
    public Result<EditUsuarioResponse, Notification> edit(EditUsuarioRequest request) throws Exception {
        String updatedBy = UUID.randomUUID().toString();
        EditUsuario command = new EditUsuario(
            request.getIdUsuario(),
            request.getNombre().trim(),
            request.getClave().trim(),
            updatedBy
        );
        Notification notification = this.editUsuarioValidator.validate(command);
        if (notification.hasErrors()) return Result.failure(notification);
        commandGateway.sendAndWait(command);
        EditUsuarioResponse editUsuarioResponse = new EditUsuarioResponse(
            command.getId(),
            command.getNombre(),
    		command.getClave()
        );
        return Result.success(editUsuarioResponse);
    }
    
} 
