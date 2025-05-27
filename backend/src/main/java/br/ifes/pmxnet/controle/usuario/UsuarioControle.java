package br.ifes.pmxnet.controle.usuario;

import br.ifes.pmxnet.applicacao.usuario.IUsuarioServico;
import br.ifes.pmxnet.applicacao.usuario.dto.UsuarioDTO;
import br.ifes.pmxnet.controle.GenericControle;
import br.ifes.pmxnet.dominio.Usuario;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControle extends GenericControle<Usuario, Long, UsuarioDTO> {

    public UsuarioControle(IUsuarioServico service) {
        super(service);
    }
}