package com.app.inventario.usuario.Interface;

import java.util.concurrent.CompletableFuture;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.app.inventario.message.queries.GetAll;
import com.app.inventario.message.queries.GetHistoryByUsuarioId;
import com.app.inventario.shared.Interface.ApiController;
import com.app.inventario.shared.application.Notification;
import com.app.inventario.shared.application.Result;
import com.app.inventario.usuario.application.dto.request.EditUsuarioRequest;
import com.app.inventario.usuario.application.dto.request.RegisterUsuarioRequest;
import com.app.inventario.usuario.application.dto.response.EditUsuarioResponse;
import com.app.inventario.usuario.application.dto.response.RegisterUsuarioResponse;
import com.app.inventario.usuario.application.queries.views.UsuarioHistoryView;
import com.app.inventario.usuario.application.queries.views.UsuarioView;
import com.app.inventario.usuario.application.services.UsuarioApplicationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Api(value="Api de usuarios")
@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios")
public class UsuarioController {
	private final UsuarioApplicationService usuarioApplicationService;
    private final QueryGateway queryGateway; 
    
    public UsuarioController(UsuarioApplicationService usuarioApplicationService, QueryGateway queryGateway) {
        this.usuarioApplicationService = usuarioApplicationService;
        this.queryGateway = queryGateway;
    }
    
    @ApiOperation(value="Registrar usuarios")
    @PostMapping(path= "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> register(@RequestBody RegisterUsuarioRequest request) {
        try {
            Result<RegisterUsuarioResponse, Notification> result = usuarioApplicationService.register(request);
            if (result.isSuccess()) return ApiController.created(result.getSuccess());
            return ApiController.error(result.getFailure().getErrors());
        } catch(Exception e) {
            e.printStackTrace();
            return ApiController.serverError();
        }
    }

    @ApiOperation(value="Actualizar usuario por id",response=List.class)
    @PutMapping("/{usuarioId}")
    public ResponseEntity<Object> edit(@PathVariable("usuarioId") String idUsuario, @RequestBody EditUsuarioRequest request) {
        try {
            request.setIdUsuario(idUsuario);
            Result<EditUsuarioResponse, Notification> result = usuarioApplicationService.edit(request);
            if (result.isSuccess()) return ApiController.ok(result.getSuccess());
            return ApiController.error(result.getFailure().getErrors());
        } catch(Exception e) {
            e.printStackTrace();
            return ApiController.serverError();
        }
    }

    @ApiOperation(value="Obtener todos los usuarios",response=List.class)
    @GetMapping("")
    @Operation(summary = "Obtener todos los usuarios")
    public ResponseEntity<Object> getAll() {
        try {
            CompletableFuture<List<UsuarioView>> future = queryGateway.query(new GetAll(), ResponseTypes.multipleInstancesOf(UsuarioView.class));
            List<UsuarioView> usuarios = future.get();
            return ApiController.ok(usuarios);
        } catch( Exception e) {
            e.printStackTrace();
            return ApiController.serverError();
        }
    }

    @ApiOperation(value="Obtener usuario por id del history",response=List.class)
    @GetMapping("/{usuarioId}/history")
    @Operation(summary = "Obtener histórico de usuarios")
    public ResponseEntity<Object> getHistoryByUsuarioId(@PathVariable("usuarioId") String idUsuario) {
        try {
            CompletableFuture<List<UsuarioHistoryView>> future = queryGateway.query(new GetHistoryByUsuarioId(idUsuario), ResponseTypes.multipleInstancesOf(UsuarioHistoryView.class));
            List<UsuarioHistoryView> usuarios = future.get();
            return ApiController.ok(usuarios);
        } catch( Exception e) {
            e.printStackTrace();
            return ApiController.serverError();
        }
    }
    
        
}
