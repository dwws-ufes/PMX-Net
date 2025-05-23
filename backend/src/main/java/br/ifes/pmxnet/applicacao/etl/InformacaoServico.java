package br.ifes.pmxnet.applicacao.etl;

import br.ifes.pmxnet.applicacao.GenericServico;
import br.ifes.pmxnet.dominio.Informacao;
import br.ifes.pmxnet.persistencia.etl.InformacaoRepository;
import br.ifes.pmxnet.seguranca.JwtUtil;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class InformacaoServico extends GenericServico<Informacao, Long> implements IInformacaoServico {

    public InformacaoServico(InformacaoRepository repository) {
        super(repository);
    }

    @Override
    public List<Informacao> listar() {
        Long currentID =  JwtUtil.getUsuarioIdDoToken();
        return ((InformacaoRepository) repository).findAllByOwnerId(currentID);
    }


    @Override
    public Informacao salvar(Informacao entity) {
        Long currentUserId =  JwtUtil.getUsuarioIdDoToken();    
        if(entity != null){
            Informacao info =  repository.getReferenceById(entity.getId());
            if(info.getUsuario().getId() == currentUserId){
                return repository.save(entity);
            }
        }
        return repository.save(entity);
    }

}
