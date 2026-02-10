package com.projetoapi.dominio;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(unique = true, nullable = false, length = 150)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private LocalDateTime dataAtualizacao;


    // Método chamado pelo JPA antes de persistir a entidade no banco.
    // Responsável por definir automaticamente as datas de criação
    // e da última atualização do usuário.
    @PrePersist
    public void prePersist() {
        dataCriacao = LocalDateTime.now();
        dataAtualizacao = LocalDateTime.now();
    }


    // Método chamado pelo JPA antes de atualizar a entidade no banco.
    // Atualiza automaticamente a data da última modificação do usuário.
    @PreUpdate
    public void preUpdate() {
        dataAtualizacao = LocalDateTime.now();
    }


}
