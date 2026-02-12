package com.projetoapi.services;

import com.projetoapi.dominio.Role;
import com.projetoapi.dominio.Usuario;
import com.projetoapi.repositorios.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // =============================
    // CADASTRAR USUARIO
    // =============================
    public Usuario cadastrarUsuario(Usuario usuario) {

        // Verificando se já existe usuário com o mesmo email
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Email já cadastrado.");
        }

        // Define role padrão
        usuario.setRole(Role.CLIENTE);

        return usuarioRepository.save(usuario);
    }

    // ============================
    // BUSCAR USUARIO POR ID
    // ============================
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado."));
    }

    // ============================
    // BUSCAR TODOS USUARIOS
    // ============================
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    // ============================
    // BUSCAR USUARIO POR EMAIL
    // ============================
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }

    // ============================
    // DELETAR USUARIO POR ID
    // ============================
    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario não encontrado.");
        }
        usuarioRepository.deleteById(id);
    }

    // ============================
    // ATUALIZAR USUARIO POR ID
    // ============================
    public Usuario atualizar(Long id, Usuario usuarioAtualizado) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        usuario.setSenha(usuarioAtualizado.getSenha());

        return usuarioRepository.save(usuario);
    }
}
