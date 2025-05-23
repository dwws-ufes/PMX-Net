package br.ifes.pmxnet.applicacao;

import java.util.List;
import java.util.Optional;

public interface IGenericServico<T, ID> {
    List<T> listar();
    Optional<T> findById(ID id);
    T salvar(T entity);
    void remover(ID id);
}
