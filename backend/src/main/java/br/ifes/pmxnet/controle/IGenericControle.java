package br.ifes.pmxnet.controle;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IGenericControle<T, ID> {

    @GetMapping
    List<T> listar();

    @GetMapping("/{id}")
    ResponseEntity<T> findById(@PathVariable ID id);

    @PostMapping
    T salvar(@RequestBody T entity);

    @DeleteMapping("/{id}")
    void remover(@PathVariable ID id);
}
