package br.ifes.pmxnet.applicacao.projeto;

import br.ifes.pmxnet.applicacao.GenericServico;
import br.ifes.pmxnet.applicacao.projeto.dto.ProjetoDTO;
import br.ifes.pmxnet.applicacao.projeto.dto.ProjetoMapper;
import br.ifes.pmxnet.dominio.Projeto;
import br.ifes.pmxnet.dominio.Usuario;
import br.ifes.pmxnet.persistencia.projeto.IProjetoRepository;
import br.ifes.pmxnet.persistencia.usuario.IUsuarioRepository;
import br.ifes.pmxnet.seguranca.JwtUtil;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ProjetoServico extends GenericServico<Projeto, Long, ProjetoDTO> implements IProjetoServico {

    @Autowired
    protected IUsuarioRepository usuarioRepository;

    public ProjetoServico(IProjetoRepository repository, ProjetoMapper projetoMapper) {
        super(repository, projetoMapper);

    }

    @Override
    public List<ProjetoDTO> listar() {
        Long currentID = JwtUtil.getUsuarioIdDoToken();
        return mapper.toDTOList(((IProjetoRepository) repository).findAllByOwnerId(currentID));
    }

    // Como a entidade generica nao conhece o usuario criado precisa ser feito aqui.
    @Override
    public ProjetoDTO salvar(ProjetoDTO entity) {
        Long currentUserId = JwtUtil.getUsuarioIdDoToken();
        if (entity.getId() != null) {
            try {
                Projeto existente = repository.getReferenceById(entity.getId());
                if (existente.isOwner(currentUserId)) {
                    Projeto projeto = mapper.toEntity(entity);
                    Projeto salvo = repository.save(projeto);
                    return mapper.toDTO(salvo);
                } else {
                    throw new PermissionDeniedDataAccessException("Sem permissão para atualizar este projeto", null);
                }
            } catch (EntityNotFoundException ex) {
                throw new EntityNotFoundException("projeto nao existe", null);
            }
        } else {
            // Criar novo projeto
            Usuario usuario = usuarioRepository.getReferenceById(currentUserId);
            Projeto projeto = mapper.toEntity(entity);
            projeto.setUsuario(usuario);
            Projeto salvo = repository.save(projeto);
            return mapper.toDTO(salvo);
        }
    }

    @Override
    public void remover(Long id) {
        Long currentUserId = JwtUtil.getUsuarioIdDoToken();
        if (id != null) {
            Projeto info = repository.getReferenceById(id);
            if (info.isOwner(currentUserId)) {
                repository.deleteById(id); // ← CORREÇÃO AQUI
            } else {
                throw new PermissionDeniedDataAccessException("Sem permissão para excluir este projeto", null);
            }
        }
    }

}
