package br.ifes.pmxnet.controle.projeto;

import br.ifes.pmxnet.applicacao.projeto.ITipoServico;
import br.ifes.pmxnet.controle.GenericControle;
import br.ifes.pmxnet.dominio.Tipo;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/tipos")

public class TipoControle extends GenericControle<Tipo, Long> {

    public TipoControle(ITipoServico service) {
        super(service);
    }
}