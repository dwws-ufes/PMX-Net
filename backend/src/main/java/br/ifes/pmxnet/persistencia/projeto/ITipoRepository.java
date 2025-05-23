package br.ifes.pmxnet.persistencia.projeto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ifes.pmxnet.dominio.Tipo;

@Repository
public interface ITipoRepository extends JpaRepository<Tipo, Long> {
}
