package br.ifes.pmxnet.applicacao.projeto.dto;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import br.ifes.pmxnet.applicacao.ConversorDTO;
import br.ifes.pmxnet.dominio.Projeto;

@Mapper(componentModel = "spring", uses = { FerramentaMapper.class }, // importante
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjetoMapper extends ConversorDTO<Projeto, ProjetoDTO> {

    @Override
    @Mapping(source = "usuario.id", target = "usuarioId")
    ProjetoDTO toDTO(Projeto entity);

    @Override
    @Mapping(source = "usuarioId", target = "usuario.id")
    Projeto toEntity(ProjetoDTO dto);

    @Override
    List<ProjetoDTO> toDTOList(List<Projeto> entityList);
}
