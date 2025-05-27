package br.ifes.pmxnet.controle.etl;

import br.ifes.pmxnet.applicacao.etl.IInformacaoServico;
import br.ifes.pmxnet.applicacao.etl.dto.InformacaoDTO;
import br.ifes.pmxnet.controle.GenericControle;
import br.ifes.pmxnet.dominio.Informacao;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/informacaos")
public class InformacaoControle extends GenericControle<Informacao, Long, InformacaoDTO> {

    public InformacaoControle(IInformacaoServico service) {
        super(service);
    }

}
