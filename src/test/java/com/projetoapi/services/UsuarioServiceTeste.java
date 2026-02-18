package com.projetoapi.services;

import com.projetoapi.dominio.Role;
import com.projetoapi.dominio.Usuario;
import com.projetoapi.dto.UsuarioRequestDTO;
import com.projetoapi.dto.UsuarioResponseDTO;
import com.projetoapi.excecoes.EmailJaCadastradoException;
import com.projetoapi.excecoes.UsuarioNaoEncontradoException;
import com.projetoapi.repositorios.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTeste {

    @Mock
    private UsuarioRepository usuarioRepository; // Mock do repository para simular comportamento sem banco real

    @InjectMocks
    private UsuarioService usuarioService; // Service real com repository mockado injetado

    // Método auxiliar para criar usuário fake reutilizável em vários testes
    private Usuario criarUsuarioMock() {
        Usuario usuario = new Usuario();
        usuario.setNome("João");
        usuario.setEmail("joao@gmail.com");
        usuario.setSenha("123456");
        usuario.setRole(Role.CLIENTE);
        return usuario;
    }

    // =========================
    // CADASTRAR
    // =========================

    @Test
    void deveCadastrarUsuarioComSucesso() {
        // Criação do DTO de cadastro
        UsuarioRequestDTO dto = new UsuarioRequestDTO();
        dto.setNome("João");
        dto.setEmail("joao@gmail.com");
        dto.setSenha("123456");

        // Mock: Simula que email ainda não existe
        when(usuarioRepository.existsByEmail(dto.getEmail())).thenReturn(false);

        // Mock: Simula que o save retorna o usuário criado
        when(usuarioRepository.save(any())).thenReturn(criarUsuarioMock());

        // Chama método real do Service
        UsuarioResponseDTO response = usuarioService.cadastrarUsuario(dto);

        // Verificações: resposta não nula e dados corretos
        assertNotNull(response);
        assertEquals("João", response.getNome());
        assertEquals("joao@gmail.com", response.getEmail());

        // Verifica se método save foi chamado
        verify(usuarioRepository).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoEmailJaExiste() {
        // Criação do DTO de cadastro com email já existente
        UsuarioRequestDTO dto = new UsuarioRequestDTO();
        dto.setNome("João");
        dto.setEmail("joao@gmail.com");
        dto.setSenha("123456");

        // Mock: Simula que email já existe no banco
        when(usuarioRepository.existsByEmail(dto.getEmail())).thenReturn(true);

        // Verifica se a exceção EmailJaCadastradoException é lançada
        assertThrows(EmailJaCadastradoException.class,
                () -> usuarioService.cadastrarUsuario(dto));

        // Garante que save nunca foi chamado
        verify(usuarioRepository, never()).save(any());
    }

    // =========================
    // BUSCAR POR ID
    // =========================

    @Test
    void deveBuscarUsuarioPorIdComSucesso() {
        // Mock: Simula retorno de usuário existente pelo ID
        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.of(criarUsuarioMock()));

        // Chama método real
        UsuarioResponseDTO response = usuarioService.buscarPorId(1L);

        // Verifica se nome retornado está correto
        assertEquals("João", response.getNome());
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontradoPorId() {
        // Mock: Simula que usuário não existe
        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.empty());

        // Verifica se exceção correta é lançada
        assertThrows(UsuarioNaoEncontradoException.class,
                () -> usuarioService.buscarPorId(1L));
    }

    // =========================
    // LISTAR TODOS
    // =========================

    @Test
    void deveListarTodosUsuarios() {
        // Mock: Simula retorno de lista com 1 usuário
        when(usuarioRepository.findAll())
                .thenReturn(List.of(criarUsuarioMock()));

        // Chama método real
        List<UsuarioResponseDTO> lista = usuarioService.listarTodos();

        // Verifica tamanho da lista e dados do primeiro usuário
        assertEquals(1, lista.size());
        assertEquals("João", lista.get(0).getNome());
    }

    // =========================
    // BUSCAR POR EMAIL
    // =========================

    @Test
    void deveBuscarUsuarioPorEmailComSucesso() {
        // Mock: Simula retorno do usuário pelo email
        when(usuarioRepository.findByEmail("joao@gmail.com"))
                .thenReturn(Optional.of(criarUsuarioMock()));

        // Chama método real
        Usuario usuario = usuarioService.buscarPorEmail("joao@gmail.com");

        // Verifica se nome do usuário está correto
        assertEquals("João", usuario.getNome());
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontradoPorEmail() {
        // Mock: Simula que email não existe
        when(usuarioRepository.findByEmail("joao@gmail.com"))
                .thenReturn(Optional.empty());

        // Verifica se exceção correta é lançada
        assertThrows(UsuarioNaoEncontradoException.class,
                () -> usuarioService.buscarPorEmail("joao@gmail.com"));
    }

    // =========================
    // DELETAR
    // =========================

    @Test
    void deveDeletarUsuarioComSucesso() {
        // Mock: Simula que usuário existe
        when(usuarioRepository.existsById(1L)).thenReturn(true);

        // Chama método real
        usuarioService.deletar(1L);

        // Verifica se deleteById foi chamado
        verify(usuarioRepository).deleteById(1L);
    }

    @Test
    void deveLancarExcecaoAoDeletarUsuarioInexistente() {
        // Mock: Simula que usuário não existe
        when(usuarioRepository.existsById(1L)).thenReturn(false);

        // Verifica se exceção correta é lançada
        assertThrows(UsuarioNaoEncontradoException.class,
                () -> usuarioService.deletar(1L));

        // Garante que deleteById nunca foi chamado
        verify(usuarioRepository, never()).deleteById(any());
    }

    // =========================
    // ATUALIZAR
    // =========================

    @Test
    void deveAtualizarUsuarioComSucesso() {
        // Usuário existente no banco
        Usuario usuarioExistente = criarUsuarioMock();

        // DTO com novos valores
        UsuarioRequestDTO dto = new UsuarioRequestDTO();
        dto.setNome("Novo Nome");
        dto.setEmail("novo@gmail.com");
        dto.setSenha("999999");

        // Mock: findById retorna usuário existente
        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.of(usuarioExistente));

        // Mock: save retorna o mesmo usuário
        when(usuarioRepository.save(any()))
                .thenReturn(usuarioExistente);

        // Chama método real
        UsuarioResponseDTO response = usuarioService.atualizar(1L, dto);

        // Verifica se os valores foram atualizados
        assertEquals("Novo Nome", usuarioExistente.getNome());
        assertEquals("novo@gmail.com", usuarioExistente.getEmail());

        // Garante que save foi chamado com o usuário correto
        verify(usuarioRepository).save(usuarioExistente);
    }

    @Test
    void deveLancarExcecaoAoAtualizarUsuarioInexistente() {
        // Mock: usuário não existe
        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.empty());

        UsuarioRequestDTO dto = new UsuarioRequestDTO();

        // Verifica se exceção correta é lançada
        assertThrows(UsuarioNaoEncontradoException.class,
                () -> usuarioService.atualizar(1L, dto));
    }

    @Test
    void deveLancarExcecaoAoAtualizarComEmailJaCadastrado() {
        // Usuário existente
        Usuario usuarioExistente = criarUsuarioMock();

        // DTO com email já cadastrado por outro usuário
        UsuarioRequestDTO dto = new UsuarioRequestDTO();
        dto.setNome("João Atualizado");
        dto.setEmail("maria@gmail.com"); // email duplicado
        dto.setSenha("123456");

        // Mock: findById retorna usuário que será atualizado
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioExistente));

        // Mock: existsByEmail retorna true, simulando outro usuário com esse email
        when(usuarioRepository.existsByEmail(dto.getEmail())).thenReturn(true);

        // Verifica se a exceção EmailJaCadastradoException é lançada
        assertThrows(EmailJaCadastradoException.class,
                () -> usuarioService.atualizar(1L, dto));

        // Garante que save nunca foi chamado
        verify(usuarioRepository, never()).save(any());
    }
}
