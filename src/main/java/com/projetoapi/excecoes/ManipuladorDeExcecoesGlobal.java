package com.projetoapi.excecoes;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ManipuladorDeExcecoesGlobal {

    // 404 - Usuário não encontrado
    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<RespostaDeErro> handleUsuarioNaoEncontrado(
            UsuarioNaoEncontradoException ex,
            HttpServletRequest request) {

        RespostaDeErro erro = new RespostaDeErro(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    // 409 - Email já cadastrado
    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<RespostaDeErro> handleEmailJaCadastrado(
            EmailJaCadastradoException ex,
            HttpServletRequest request) {

        RespostaDeErro erro = new RespostaDeErro(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    // 500 - Erro inesperado
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespostaDeErro> handleExcecaoGenerica(
            Exception ex,
            HttpServletRequest request) {

        RespostaDeErro erro = new RespostaDeErro(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "Erro interno inesperado",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}
