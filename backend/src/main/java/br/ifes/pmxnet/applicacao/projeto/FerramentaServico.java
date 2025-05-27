package br.ifes.pmxnet.applicacao.projeto;

import br.ifes.pmxnet.applicacao.GenericServico;
import br.ifes.pmxnet.applicacao.projeto.dto.FerramentaDTO;
import br.ifes.pmxnet.applicacao.projeto.dto.FerramentaMapper;
import br.ifes.pmxnet.dominio.Ferramenta;
import br.ifes.pmxnet.dominio.Projeto;
import br.ifes.pmxnet.persistencia.projeto.IFerramentaRepository;
import br.ifes.pmxnet.persistencia.projeto.IProjetoRepository;
import br.ifes.pmxnet.seguranca.JwtUtil;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class FerramentaServico extends GenericServico<Ferramenta, Long, FerramentaDTO> implements IFerramentaServico {

    @Autowired
    protected IProjetoRepository projetoRepository;

    public FerramentaServico(IFerramentaRepository repository, FerramentaMapper ferramentaMapper) {
        super(repository, ferramentaMapper);
    }

    @Override
    public List<FerramentaDTO> listar() {
        Long currentID = JwtUtil.getUsuarioIdDoToken();
        return mapper.toDTOList(((IFerramentaRepository) repository).findAllByOwnerId(currentID));
    }

    @Override
    public FerramentaDTO salvar(FerramentaDTO entity) {
        Long currentUserId = JwtUtil.getUsuarioIdDoToken();

        // Evita chamada com ID nulo para update
        if (entity.getId() != null) {
            try {
                Ferramenta existente = repository.getReferenceById(entity.getId());

                if (existente.isOwner(currentUserId)) {
                    Ferramenta ferramenta = mapper.toEntity(entity);
                    ferramenta.setProjeto(existente.getProjeto()); // mantém associação original
                    Ferramenta salvo = repository.save(ferramenta);
                    return mapper.toDTO(salvo);
                } else {
                    throw new PermissionDeniedDataAccessException("Sem permissão para alterar essa ferramenta", null);
                }

            } catch (EntityNotFoundException ex) {
                // se entity.getId() não existe no banco
                throw new IllegalArgumentException("Ferramenta com ID informado não encontrada", ex);
            }
        }

        // Criação de nova ferramenta
        if (entity.getProjetoId() == null) {
            throw new IllegalArgumentException("projetoId não pode ser nulo");
        }

        Projeto projeto = projetoRepository.getReferenceById(entity.getProjetoId());

        if (!projeto.isOwner(currentUserId)) {
            throw new PermissionDeniedDataAccessException("Sem permissão para adicionar ferramenta neste projeto",
                    null);
        }

        Ferramenta nova = mapper.toEntity(entity);
        nova.setProjeto(projeto);
        Ferramenta salvo = repository.save(nova);
        return mapper.toDTO(salvo);
    }

    @Override
    public void remover(Long id) {
        Long currentUserId = JwtUtil.getUsuarioIdDoToken();
        if (id != null) {
            Ferramenta info = repository.getReferenceById(id);
            if (info.isOwner(currentUserId)) {
                ((FerramentaServico) repository).remover(id);
            } else {
                System.out.println("ID:" + id);
            }
        }
    }

}
