package com.projetoapi.controllers;

import com.projetoapi.dominio.Usuario;
import com.projetoapi.dto.UsuarioRequestDTO;
import com.projetoapi.dto.UsuarioResponseDTO;
import com.projetoapi.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    // ===========================
    // BUSCAR TODOS USUARIOS
    // ===========================
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {

        return ResponseEntity.ok(service.listarTodos());
    }

    // ===========================
    // BUSCAR USUARIO POR ID
    // ===========================
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // ===========================
    // CADASTRAR USUARIO
    // ===========================
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrar(
            @Valid @RequestBody UsuarioRequestDTO usuarioDTO) {

        UsuarioResponseDTO response = service.cadastrarUsuario(usuarioDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ===========================
    // DELETAR USUARIO POR ID
    // ===========================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // ===========================
    // ATUALIZAR USUARIO POR ID
    // ===========================
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRequestDTO usuarioDTO) {

        return ResponseEntity.ok(service.atualizar(id, usuarioDTO));
    }
}
