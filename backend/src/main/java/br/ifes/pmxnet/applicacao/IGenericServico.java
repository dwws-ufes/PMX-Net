package br.ifes.pmxnet.applicacao;

import java.util.List;
import java.util.Optional;

public interface IGenericServico<T, ID, DTO> {

    Optional<DTO> findById(ID id);

    void remover(ID id);

    // Operações com DTO
    List<DTO> listar();

    DTO salvar(DTO dto);
}
