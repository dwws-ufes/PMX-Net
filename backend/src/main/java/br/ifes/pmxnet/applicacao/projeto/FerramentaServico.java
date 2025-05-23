package br.ifes.pmxnet.applicacao.projeto;

import br.ifes.pmxnet.applicacao.GenericServico;
import br.ifes.pmxnet.dominio.Ferramenta;
import br.ifes.pmxnet.dominio.Projeto;
import br.ifes.pmxnet.persistencia.projeto.IFerramentaRepository;
import br.ifes.pmxnet.persistencia.projeto.IProjetoRepository;
import br.ifes.pmxnet.seguranca.JwtUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FerramentaServico extends GenericServico<Ferramenta, Long> implements IFerramentaServico {

    @Autowired
    IProjetoRepository projetoRepository;

    public FerramentaServico(IFerramentaRepository repository) {
        super(repository);
    }

    @Override
    public List<Ferramenta> listar() {
        Long currentID = JwtUtil.getUsuarioIdDoToken();
        return ((IFerramentaRepository) repository).findAllByOwnerId(currentID);
    }

    @Override
    public Ferramenta salvar(Ferramenta entity) {
        Long currentUserId = JwtUtil.getUsuarioIdDoToken();
        if (entity != null) {
            Ferramenta info = repository.getReferenceById(entity.getId());
            if (info.getUsuario().getId() == currentUserId) {
                return repository.save(entity);
            }
        }

        Projeto projeto = projetoRepository.getReferenceById(entity.getProjeto().getId());
        if (projeto != null) {
            if (projeto.getUsuario().getId() != currentUserId) {
                entity.setProjeto(projeto);

            } else {
                return null;// disparar exception.
            }
        }
        return repository.save(entity);

    }

    @Override
    public void remover(Long id) {
        Long currentUserId = JwtUtil.getUsuarioIdDoToken();
        if (id != null) {
            Ferramenta info = repository.getReferenceById(id);
            if (info.getUsuario().getId() == currentUserId) {
                ((FerramentaServico) repository).remover(id);
            } else {
                System.out.println("ID:" + id);
            }
        }
    }

}
