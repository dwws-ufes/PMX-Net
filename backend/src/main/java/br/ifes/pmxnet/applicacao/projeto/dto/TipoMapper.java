package br.ifes.pmxnet.applicacao.projeto.dto;

import java.util.List;
import org.mapstruct.Mapper;

import br.ifes.pmxnet.applicacao.ConversorDTO;
import br.ifes.pmxnet.dominio.Tipo;

@Mapper(componentModel = "spring")
public interface TipoMapper extends ConversorDTO<Tipo, TipoDTO> {
    @Override
    TipoDTO toDTO(Tipo entity);

    @Override
    Tipo toEntity(TipoDTO dto);

    @Override
    List<TipoDTO> toDTOList(List<Tipo> entityList);
}
