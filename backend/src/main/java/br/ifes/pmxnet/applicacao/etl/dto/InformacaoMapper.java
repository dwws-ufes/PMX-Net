package br.ifes.pmxnet.applicacao.etl.dto;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import br.ifes.pmxnet.applicacao.ConversorDTO;
import br.ifes.pmxnet.applicacao.projeto.dto.ProjetoMapper;
import br.ifes.pmxnet.dominio.Informacao;

@Mapper(componentModel = "spring", uses = { ProjetoMapper.class }, // importante
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InformacaoMapper extends ConversorDTO<Informacao, InformacaoDTO> {
    @Override
    @Mapping(source = "projeto.id", target = "projetoId")
    InformacaoDTO toDTO(Informacao entity);

    Informacao toEntity(InformacaoDTO dto);

    @Override
    List<InformacaoDTO> toDTOList(List<Informacao> entityList);
}
