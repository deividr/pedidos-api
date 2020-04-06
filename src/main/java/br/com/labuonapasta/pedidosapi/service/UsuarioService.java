package br.com.labuonapasta.pedidosapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.labuonapasta.pedidosapi.model.Usuario;
import br.com.labuonapasta.pedidosapi.repository.UsuarioRepository;

/**
 * Serviços do recurso usuário.
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Efetuar alterações no usuário do código informado no primeiro parâmetro.
     * 
     * @param codigo  código do usuário que será alterado.
     * @param usuario informações que serão alteradas.
     * @return
     */
    public Usuario alterar(Long codigo, Usuario usuario) {
        Usuario usuarioSalvo = bucarPeloCodigo(codigo);

        BeanUtils.copyProperties(usuario, usuarioSalvo, "userId");

        return usuarioRepository.save(usuarioSalvo);
    }

    /**
     * Ativar ou desativar o usuário. Usar essa opção ao invés de excluir o usuário,
     * visto que ele pode estar associado a pedidos antigos e precisa de histórico.
     * 
     * @param codigo código do usuário que será ativado/desativado
     * @param ativo  true = ativar, false = desativar.
     */
    public void ativarDesativar(Long codigo, Boolean ativo) {
        Usuario usuarioSalvo = bucarPeloCodigo(codigo);

        usuarioSalvo.setAtivo(ativo);

        usuarioRepository.save(usuarioSalvo);
    }

    /**
     * Buscar o um usuário pelo código.
     * 
     * @param codigo código do usuário que se deseja obter
     * @return
     */
    public Usuario bucarPeloCodigo(Long codigo) {
        Optional<Usuario> usuarioSalvo = usuarioRepository.findById(codigo);

        if (!usuarioSalvo.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        return usuarioSalvo.get();
    }

}