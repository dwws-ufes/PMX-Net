package br.ifes.pmxnet.controle.projeto;

import br.ifes.pmxnet.applicacao.projeto.IProjetoServico;
import br.ifes.pmxnet.applicacao.projeto.dto.ProjetoDTO;
import br.ifes.pmxnet.controle.GenericControle;
import br.ifes.pmxnet.dominio.Projeto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projetos")
@SecurityRequirement(name = "bearerAuth") // Exige JWT
public class ProjetoControle extends GenericControle<Projeto, Long, ProjetoDTO> {

    public ProjetoControle(IProjetoServico service) {
        super(service);
    }

}