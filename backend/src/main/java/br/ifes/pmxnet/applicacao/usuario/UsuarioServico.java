package br.ifes.pmxnet.applicacao.usuario;

import br.ifes.pmxnet.applicacao.GenericServico;
import br.ifes.pmxnet.applicacao.usuario.dto.UsuarioDTO;
import br.ifes.pmxnet.applicacao.usuario.dto.UsuarioMapper;
import br.ifes.pmxnet.dominio.Usuario;
import br.ifes.pmxnet.persistencia.usuario.IUsuarioRepository;

import org.springframework.stereotype.Service;

@Service
public class UsuarioServico extends GenericServico<Usuario, Long, UsuarioDTO> implements IUsuarioServico {

    public UsuarioServico(IUsuarioRepository repository, UsuarioMapper usuarioMapper) {
        super(repository, usuarioMapper);
    }

}
