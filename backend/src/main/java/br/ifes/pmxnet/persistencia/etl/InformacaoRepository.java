package br.ifes.pmxnet.persistencia.etl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ifes.pmxnet.dominio.Informacao;


@Repository
public interface InformacaoRepository extends JpaRepository<Informacao, Long> {

   @Query("SELECT i FROM Informacao i WHERE i.projeto.criador.id = :ownerId")
    List<Informacao> findAllByOwnerId(@Param("ownerId") Long ownerId);
}