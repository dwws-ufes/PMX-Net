package br.ifes.pmxnet.controle.etl;

import br.ifes.pmxnet.applicacao.etl.IInformacaoServico;
import br.ifes.pmxnet.controle.GenericControle;
import br.ifes.pmxnet.dominio.Informacao;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/informacaos")
public class InformacaoControle extends GenericControle<Informacao, Long> {


   public InformacaoControle(IInformacaoServico service) {
        super(service);
    }



    @GetMapping("/api/informacaos/")
    public List<Informacao> listar() {
        return ((IInformacaoServico)service).listar();
    }
}
