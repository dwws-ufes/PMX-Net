package br.ifes.pmxnet.applicacao.etl;

import br.ifes.pmxnet.applicacao.GenericServico;
import br.ifes.pmxnet.applicacao.etl.dto.InformacaoDTO;
import br.ifes.pmxnet.applicacao.etl.dto.InformacaoMapper;
import br.ifes.pmxnet.dominio.Informacao;
import br.ifes.pmxnet.dominio.Projeto;
import br.ifes.pmxnet.persistencia.etl.InformacaoRepository;
import br.ifes.pmxnet.persistencia.projeto.IProjetoRepository;
import br.ifes.pmxnet.seguranca.JwtUtil;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class InformacaoServico extends GenericServico<Informacao, Long, InformacaoDTO> implements IInformacaoServico {

    @Autowired
    protected IProjetoRepository projetoRepository;

    public InformacaoServico(InformacaoRepository repository, InformacaoMapper informacaoMapper) {
        super(repository, informacaoMapper);

    }

    @Override
    public List<InformacaoDTO> listar() {
        Long currentID = JwtUtil.getUsuarioIdDoToken();
        return mapper.toDTOList(((InformacaoRepository) repository).findAllByOwnerId(currentID));
    }

    @Override
    public InformacaoDTO salvar(InformacaoDTO entity) {
        Long currentUserId = JwtUtil.getUsuarioIdDoToken();
        try {
            // is that an update?
            Informacao info = repository.getReferenceById(entity.getId());
            if (info.isOwner(currentUserId)) {
                Informacao ferramenta = mapper.toEntity(entity);
                Informacao salvo = repository.save(ferramenta);
                return mapper.toDTO(salvo);
            }
        } catch (EntityNotFoundException ex) {
            // this is not an update, then let´s create it.
            Projeto projeto = projetoRepository.getReferenceById(entity.getProjetoId());
            if (projeto.isOwner(currentUserId)) {// se eh o dono pode salvar
                Informacao Informacao = mapper.toEntity(entity);
                Informacao salvo = repository.save(Informacao);
                return mapper.toDTO(salvo);
            } else {
                throw new PermissionDeniedDataAccessException("Sem permissao para alterar esse recurso", ex);
            }

        }
        return null;
    }
}
