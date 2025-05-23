package br.ifes.pmxnet.applicacao.projeto;

import br.ifes.pmxnet.applicacao.GenericServico;
import br.ifes.pmxnet.dominio.Projeto;
import br.ifes.pmxnet.dominio.Usuario;
import br.ifes.pmxnet.persistencia.projeto.IProjetoRepository;
import br.ifes.pmxnet.persistencia.usuario.IUsuarioRepository;
import br.ifes.pmxnet.seguranca.JwtUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjetoServico extends GenericServico<Projeto, Long> implements IProjetoServico {

    @Autowired
    protected  IUsuarioRepository usuarioRepository;

    public ProjetoServico(IProjetoRepository repository) {
        super(repository);
    }

    @Override
    public List<Projeto> listar() {
        Long currentID =  JwtUtil.getUsuarioIdDoToken();
        return ((IProjetoRepository) repository).findAllByOwnerId(currentID);
    }

    //como a entidade generica nao conhece o usuario criado precisa ser feito aqui.
    public Projeto salvar(Projeto entity) {
        Long currentUserId =  JwtUtil.getUsuarioIdDoToken();
        if (entity != null) {//atualizar.
            Projeto info = repository.getReferenceById(entity.getId());
            if (info.getUsuario().getId() == currentUserId) {
                return repository.save(entity);
            }
        }
        Usuario usuario = usuarioRepository.getReferenceById(currentUserId);
        entity.setUsuario(usuario);
        return repository.save(entity);
    }

        @Override
    public void remover(Long id) {
        Long currentUserId = 1L;     
        if(id != null){
            Projeto info =  repository.getReferenceById(id);
            if(info.getUsuario().getId() == currentUserId){
               ((ProjetoServico) repository).remover(id);
            }else{
                System.out.println("ID:"+ id);
            }
        }
    }

}
