package br.ifes.pmxnet.persistencia.projeto;

import br.ifes.pmxnet.dominio.Ferramenta;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFerramentaRepository extends JpaRepository<Ferramenta, Long> {

    @Query("SELECT f FROM Ferramenta f WHERE f.projeto.criador.id = :id")
    List<Ferramenta> findAllByOwnerId(@Param("id") Long id);
}
