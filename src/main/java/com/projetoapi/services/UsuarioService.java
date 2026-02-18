package com.projetoapi.services;

import com.projetoapi.dominio.Role;
import com.projetoapi.dominio.Usuario;
import com.projetoapi.dto.UsuarioRequestDTO;
import com.projetoapi.dto.UsuarioResponseDTO;
import com.projetoapi.excecoes.EmailJaCadastradoException;
import com.projetoapi.excecoes.UsuarioNaoEncontradoException;
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
    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO usuarioDTO) {

        // Verificando se já existe usuário com o mesmo email
        if (usuarioRepository.existsByEmail(usuarioDTO.getEmail())) {
            throw new EmailJaCadastradoException("Email já cadastrado.");
        }

        // Conversão manual de DTO para entidade
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());

        // Define role padrão
        usuario.setRole(Role.CLIENTE);

        Usuario salvo = usuarioRepository.save(usuario);

        return toResponseDTO(salvo);
    }

    // ============================
    // BUSCAR USUARIO POR ID
    // ============================
    public UsuarioResponseDTO buscarPorId(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario não encontrado."));

        return toResponseDTO(usuario);
    }

    // ============================
    // BUSCAR TODOS USUARIOS
    // ============================
    public List<UsuarioResponseDTO> listarTodos() {

        return usuarioRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // ============================
    // BUSCAR USUARIO POR EMAIL
    // ============================
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario não encontrado"));
    }

    // ============================
    // DELETAR USUARIO POR ID
    // ============================
    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioNaoEncontradoException("Usuario não encontrado.");
        }
        usuarioRepository.deleteById(id);
    }

    // ============================
    // ATUALIZAR USUARIO POR ID
    // ============================
    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO usuarioDTO) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario não encontrado"));

        // Verificando se o novo email já existe para outro usuário
        if (usuarioRepository.existsByEmail(usuarioDTO.getEmail())
                && !usuario.getEmail().equals(usuarioDTO.getEmail())) {
            throw new EmailJaCadastradoException("Email já cadastrado.");
        }

        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());

        Usuario atualizado = usuarioRepository.save(usuario);

        return toResponseDTO(atualizado);
    }


    // Método responsável por conversão entidade -> DTO
    // Centraliza transformação e evita repetição de código
    private UsuarioResponseDTO toResponseDTO(Usuario usuario) {

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole().name(),
                usuario.getDataCriacao()
        );
    }
}
