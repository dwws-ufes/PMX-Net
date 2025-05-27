package br.ifes.pmxnet.applicacao.usuario.dto;

import java.util.List;
import org.mapstruct.Mapper;
import br.ifes.pmxnet.applicacao.ConversorDTO;
import br.ifes.pmxnet.dominio.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends ConversorDTO<Usuario, UsuarioDTO> {

    @Override
    UsuarioDTO toDTO(Usuario entity);

    @Override
    Usuario toEntity(UsuarioDTO dto);

    @Override
    List<UsuarioDTO> toDTOList(List<Usuario> entityList);
}
