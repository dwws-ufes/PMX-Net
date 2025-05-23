package br.ifes.pmxnet.applicacao.usuario;

import br.ifes.pmxnet.applicacao.GenericServico;
import br.ifes.pmxnet.dominio.Usuario;
import br.ifes.pmxnet.persistencia.usuario.IUsuarioRepository;

import org.springframework.stereotype.Service;

@Service
public class UsuarioServico extends GenericServico<Usuario, Long> implements IUsuarioServico {

    public UsuarioServico(IUsuarioRepository repository) {
        super(repository);
    }
}
