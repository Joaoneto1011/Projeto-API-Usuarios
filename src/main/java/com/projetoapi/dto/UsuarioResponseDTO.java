package com.projetoapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UsuarioResponseDTO {

    private final Long id;
    private final String nome;
    private final String email;
    private final String role;
    private final LocalDateTime dataCriacao;

}
