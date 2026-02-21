package com.projetoapi.controllers;

import com.projetoapi.dominio.Role;
import com.projetoapi.dominio.Usuario;
import com.projetoapi.dto.UsuarioRequestDTO;
import com.projetoapi.repositorios.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Transactional
class UsuarioControllerTesteIntegracao {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UsuarioRepository repository;

    // ================================
    // CADASTRAR
    // ================================
    @Test
    @DisplayName("Deve cadastrar usuário com sucesso")
    void deveCadastrarUsuarioComSucesso() throws Exception {

        UsuarioRequestDTO request = new UsuarioRequestDTO();
        request.setNome("Joao");
        request.setEmail("joao@gmail.com");
        request.setSenha("123456");

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").value("Joao"))
                .andExpect(jsonPath("$.email").value("joao@gmail.com"))
                .andExpect(jsonPath("$.role").exists())
                .andExpect(jsonPath("$.dataCriacao").exists());
    }

    // ================================
    // LISTAR
    // ================================
    @Test
    @DisplayName("Deve listar todos usuários")
    void deveListarTodosUsuarios() throws Exception {

        repository.save(new Usuario("Ana", "ana@gmail.com", "123456", Role.CLIENTE));

        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    // ================================
    // BUSCAR POR ID
    // ================================
    @Test
    @DisplayName("Deve buscar usuário por ID")
    void deveBuscarUsuarioPorId() throws Exception {

        Usuario usuario = repository.save(
                new Usuario("Maria", "maria@gmail.com", "123456", Role.CLIENTE)
        );

        mockMvc.perform(get("/usuarios/" + usuario.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(usuario.getId()))
                .andExpect(jsonPath("$.email").value("maria@gmail.com"));
    }

    // ================================
    // ERRO 404
    // ================================
    @Test
    @DisplayName("Deve retornar 404 quando usuário não encontrado")
    void deveRetornar404QuandoUsuarioNaoEncontrado() throws Exception {

        mockMvc.perform(get("/usuarios/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.erro").value("Not Found"));
    }

    // ================================
    // ATUALIZAR
    // ================================
    @Test
    @DisplayName("Deve atualizar usuário com sucesso")
    void deveAtualizarUsuarioComSucesso() throws Exception {

        Usuario usuario = repository.save(
                new Usuario("Pedro", "pedro@gmail.com", "123456", Role.CLIENTE)
        );

        UsuarioRequestDTO request = new UsuarioRequestDTO();
        request.setNome("Pedro Atualizado");
        request.setEmail("pedro@gmail.com");
        request.setSenha("123456");

        mockMvc.perform(put("/usuarios/" + usuario.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Pedro Atualizado"));
    }

    // ================================
    // DELETE
    // ================================
    @Test
    @DisplayName("Deve deletar usuário com sucesso")
    void deveDeletarUsuarioComSucesso() throws Exception {

        Usuario usuario = repository.save(
                new Usuario("Lucas", "lucas@gmail.com", "123456", Role.CLIENTE)
        );

        mockMvc.perform(delete("/usuarios/" + usuario.getId()))
                .andExpect(status().isNoContent());
    }

    // ================================
    // EMAIL DUPLICADO (409)
    // ================================
    @Test
    @DisplayName("Deve retornar 409 quando email já existe")
    void deveRetornar409QuandoEmailJaExiste() throws Exception {

        repository.save(new Usuario("Carlos", "carlos@gmail.com", "123456", Role.CLIENTE));

        UsuarioRequestDTO request = new UsuarioRequestDTO();
        request.setNome("Carlos 2");
        request.setEmail("carlos@gmail.com");
        request.setSenha("123456");

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status").value(409))
                .andExpect(jsonPath("$.erro").value("Conflict"));
    }

    // ================================
    // VALIDAÇÃO (400)
    // ================================
    @Test
    @DisplayName("Deve retornar 400 quando dados inválidos")
    void deveRetornar400QuandoDadosInvalidos() throws Exception {

        UsuarioRequestDTO request = new UsuarioRequestDTO();
        request.setNome("");
        request.setEmail("email-invalido");
        request.setSenha("123");

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.erro").value("Bad Request"));
    }
}