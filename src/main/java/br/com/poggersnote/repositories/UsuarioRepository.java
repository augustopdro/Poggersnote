package br.com.poggersnote.repositories;

import br.com.poggersnote.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT u FROM T_PN_USUARIO u WHERE u.email = ?1 AND u.senha = ?2")
    Optional<Usuario> buscarCredenciais(String email, String senha);
}
