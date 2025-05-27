package br.ifes.pmxnet.applicacao.projeto;

import br.ifes.pmxnet.applicacao.GenericServico;
import br.ifes.pmxnet.applicacao.projeto.dto.TipoDTO;
import br.ifes.pmxnet.applicacao.projeto.dto.TipoMapper;
import br.ifes.pmxnet.dominio.Tipo;
import br.ifes.pmxnet.persistencia.projeto.ITipoRepository;

import org.springframework.stereotype.Service;

@Service
public class TipoServico extends GenericServico<Tipo, Long, TipoDTO> implements ITipoServico {

    public TipoServico(ITipoRepository repository, TipoMapper tipoMapper) {
        super(repository, tipoMapper);

    }
}
