package br.ifes.pmxnet.controle;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.ifes.pmxnet.applicacao.IGenericServico;

import java.util.List;

public abstract class GenericControle<T, ID> implements IGenericControle<T, ID> {

    protected  IGenericServico<T, ID> service;


     public GenericControle(IGenericServico<T, ID> service) {
        this.service =  service;
    }

    @GetMapping
    public List<T> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> findById(@PathVariable ID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public T salvar(@RequestBody T entity) {
        return service.salvar(entity);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable ID id) {
        service.remover(id);
    }
}
