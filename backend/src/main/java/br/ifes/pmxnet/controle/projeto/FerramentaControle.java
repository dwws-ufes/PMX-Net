package br.ifes.pmxnet.controle.projeto;

import br.ifes.pmxnet.applicacao.projeto.IFerramentaServico;
import br.ifes.pmxnet.controle.GenericControle;
import br.ifes.pmxnet.dominio.Ferramenta;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ferramentas")
@SecurityRequirement(name = "bearerAuth") // Exige JWT
public class FerramentaControle extends GenericControle<Ferramenta, Long> {

    public FerramentaControle(IFerramentaServico service) {
        super(service);
    }

    @Override
    @GetMapping
    public List<Ferramenta> listar() {
        return ((IFerramentaServico)service).listar();
    }
}
