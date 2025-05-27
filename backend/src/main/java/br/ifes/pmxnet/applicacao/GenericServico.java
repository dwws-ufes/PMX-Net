package br.ifes.pmxnet.applicacao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class GenericServico<T, ID, DTO> implements IGenericServico<T, ID, DTO> {

    protected final JpaRepository<T, ID> repository;
    protected final ConversorDTO<T, DTO> mapper;

    public GenericServico(JpaRepository<T, ID> repository, ConversorDTO<T, DTO> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Optional<DTO> findById(ID id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public void remover(ID id) {
        repository.deleteById(id);
    }

    // Métodos com DTO
    public List<DTO> listar() {
        return mapper.toDTOList(repository.findAll());
    }

    public DTO salvar(DTO dto) {
        T entity = mapper.toEntity(dto);
        T salvo = repository.save(entity);
        return mapper.toDTO(salvo);
    }
}
