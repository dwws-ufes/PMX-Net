package br.ifes.pmxnet.applicacao.projeto.dto;

import java.util.List;
import org.mapstruct.Mapper;

import br.ifes.pmxnet.applicacao.ConversorDTO;
import br.ifes.pmxnet.dominio.Tipo;

@Mapper(componentModel = "spring")
public interface TipoMapper extends ConversorDTO<Tipo, br.ifes.pmxnet.applicacao.projeto.dto.TipoDTO> {
    @Override
    br.ifes.pmxnet.applicacao.projeto.dto.TipoDTO toDTO(Tipo entity);

    @Override
    Tipo toEntity(br.ifes.pmxnet.applicacao.projeto.dto.TipoDTO dto);

    @Override
    List<br.ifes.pmxnet.applicacao.projeto.dto.TipoDTO> toDTOList(List<Tipo> entityList);
}
