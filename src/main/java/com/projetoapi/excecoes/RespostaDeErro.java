package com.projetoapi.excecoes;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RespostaDeErro {

    private final LocalDateTime dataHora = LocalDateTime.now();
    private final int status;
    private final String erro;
    private final String mensagem;
    private final String caminho;

    public RespostaDeErro(int status, String erro, String mensagem, String caminho) {
        this.status = status;
        this.erro = erro;
        this.mensagem = mensagem;
        this.caminho = caminho;
    }

}
