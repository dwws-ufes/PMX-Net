package br.ifes.pmxnet.applicacao.projeto.dto;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.ifes.pmxnet.applicacao.ConversorDTO;
import br.ifes.pmxnet.dominio.Ferramenta;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = { ProjetoMapper.class }, // importante
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FerramentaMapper extends ConversorDTO<Ferramenta, FerramentaDTO> {
    @Override
    @Mapping(source = "projeto.id", target = "projetoId")
    FerramentaDTO toDTO(Ferramenta entity);

    @Override
    @Mapping(source = "projetoId", target = "projeto.id")
    Ferramenta toEntity(FerramentaDTO dto);

    @Override
    List<FerramentaDTO> toDTOList(List<Ferramenta> entityList);
}
