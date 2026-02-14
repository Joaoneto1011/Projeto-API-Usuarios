package com.projetoapi.excecoes;

public class UsuarioNaoEncontradoException extends RuntimeException {

    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
