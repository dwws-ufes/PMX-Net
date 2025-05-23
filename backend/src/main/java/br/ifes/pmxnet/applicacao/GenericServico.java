package br.ifes.pmxnet.applicacao;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public abstract class GenericServico<T, ID> implements IGenericServico<T, ID> {

    protected final JpaRepository<T, ID> repository;

    public GenericServico(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public List<T> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public T salvar(T entity) {
        return repository.save(entity);
    }

    @Override
    public void remover(ID id) {
        repository.deleteById(id);
    }



}
