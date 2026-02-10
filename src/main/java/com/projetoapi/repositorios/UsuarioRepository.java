package com.projetoapi.repositorios;

import com.projetoapi.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
