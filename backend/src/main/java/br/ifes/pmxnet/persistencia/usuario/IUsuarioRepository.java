package br.ifes.pmxnet.persistencia.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ifes.pmxnet.dominio.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
}
