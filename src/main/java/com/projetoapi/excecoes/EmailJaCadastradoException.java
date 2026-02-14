package com.projetoapi.excecoes;

public class EmailJaCadastradoException extends RuntimeException{

    public EmailJaCadastradoException(String mensagem) {
        super(mensagem);
    }
}
