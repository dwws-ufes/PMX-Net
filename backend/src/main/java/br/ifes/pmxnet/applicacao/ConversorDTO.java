package br.ifes.pmxnet.applicacao;

import java.util.List;

public interface ConversorDTO<T, DTO> {
    DTO toDTO(T entity);

    T toEntity(DTO dto);

    List<DTO> toDTOList(List<T> entityList);

}
