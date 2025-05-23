package br.ifes.pmxnet.persistencia.projeto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.ifes.pmxnet.dominio.Projeto;

@Repository
public interface IProjetoRepository extends JpaRepository<Projeto, Long> {

        @Query("SELECT i FROM Projeto i WHERE i.criador.id = :ownerId")
        List<Projeto> findAllByOwnerId(@Param("ownerId") Long id);
}
