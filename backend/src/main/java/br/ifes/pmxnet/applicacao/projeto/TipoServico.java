package br.ifes.pmxnet.applicacao.projeto;

import br.ifes.pmxnet.applicacao.GenericServico;
import br.ifes.pmxnet.dominio.Tipo;
import br.ifes.pmxnet.persistencia.projeto.ITipoRepository;

import org.springframework.stereotype.Service;

@Service
public class TipoServico extends GenericServico<Tipo, Long> implements ITipoServico {

    public TipoServico(ITipoRepository repository) {
        super(repository);
    }
}
