package br.ifes.pmxnet.controle.projeto;

import br.ifes.pmxnet.applicacao.projeto.IProjetoServico;
import br.ifes.pmxnet.controle.GenericControle;
import br.ifes.pmxnet.dominio.Projeto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/projetos")
@SecurityRequirement(name = "bearerAuth") // Exige JWT
public class ProjetoControle extends GenericControle<Projeto, Long> {

    public ProjetoControle(IProjetoServico service) {
        super(service);
    }


    @Override
    @GetMapping
    public List<Projeto> listar() {
        return ((IProjetoServico)service).listar();
    }

}