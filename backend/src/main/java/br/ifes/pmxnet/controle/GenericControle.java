package br.ifes.pmxnet.controle;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.ifes.pmxnet.applicacao.IGenericServico;

import java.util.List;

public abstract class GenericControle<T, ID, DTO> implements IGenericControle<T, ID, DTO> {

    protected IGenericServico<T, ID, DTO> service;

    public GenericControle(IGenericServico<T, ID, DTO> service) {
        this.service = service;
    }

    @GetMapping
    public List<DTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTO> findById(@PathVariable ID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DTO salvar(@RequestBody DTO entity) {
        return service.salvar(entity);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable ID id) {
        service.remover(id);
    }
}
