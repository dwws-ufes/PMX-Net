package br.ifes.pmxnet.controle.projeto;

import br.ifes.pmxnet.applicacao.projeto.ITipoServico;
import br.ifes.pmxnet.controle.GenericControle;
import br.ifes.pmxnet.dominio.Tipo;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/tipos")
@SecurityRequirement(name = "bearerAuth") // Exige JWT
public class TipoControle extends GenericControle<Tipo, Long> {

    public TipoControle(ITipoServico service) {
        super(service);
    }
}