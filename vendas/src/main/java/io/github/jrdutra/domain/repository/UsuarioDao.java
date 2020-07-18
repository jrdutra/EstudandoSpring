package io.github.jrdutra.domain.repository;

import io.github.jrdutra.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioDao extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByLogin(String login);

}
