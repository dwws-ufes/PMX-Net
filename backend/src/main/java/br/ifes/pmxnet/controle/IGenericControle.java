package br.ifes.pmxnet.controle;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IGenericControle<T, ID, DTO> {

    @GetMapping
    List<DTO> listar();

    @GetMapping("/{id}")
    ResponseEntity<DTO> findById(@PathVariable ID id);

    @PostMapping
    DTO salvar(@RequestBody DTO entity);

    @DeleteMapping("/{id}")
    void remover(@PathVariable ID id);
}
