package br.com.poggersnote.repositories;

import br.com.poggersnote.models.Registro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {
    @Query("SELECT r FROM T_PN_REGISTRO r WHERE r.usuario.id = :usuarioId")
    Page<Registro> getAllRegisters(long usuarioId , Pageable pageable);
}
