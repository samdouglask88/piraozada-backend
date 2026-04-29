package piraozada.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import piraozada.domain.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);
    // Spring Data JPA gera o SQL automaticamente pelo nome do método
    // "findBy" + "Username" = SELECT * FROM usuarios WHERE username = ?
}