package br.ifes.pmxnet.applicacao.usuario;

import br.ifes.pmxnet.applicacao.IGenericServico;
import br.ifes.pmxnet.applicacao.usuario.dto.UsuarioDTO;
import br.ifes.pmxnet.dominio.Usuario;

public interface IUsuarioServico extends IGenericServico<Usuario, Long, UsuarioDTO> {
}
