package br.ifes.pmxnet.applicacao.etl;

import br.ifes.pmxnet.applicacao.IGenericServico;
import br.ifes.pmxnet.applicacao.etl.dto.InformacaoDTO;
import br.ifes.pmxnet.dominio.Informacao;

public interface IInformacaoServico extends IGenericServico<Informacao, Long, InformacaoDTO> {

}
