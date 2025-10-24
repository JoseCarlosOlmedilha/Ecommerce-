package demoApp.Validation.UsuarioValidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import demoApp.Dto.UsuarioDTO;
import demoApp.Exception.UsuarioException;
import demoApp.Repository.UsuarioRepository;

@Component
public class ValidadorDeUsuarioJaExistente implements ValidadorDeUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void validar(UsuarioDTO usuarioDTO) {
        
        String loginUsuario = usuarioDTO.getLogin();

        UserDetails usuarioDoBanco = usuarioRepository.findByLogin(loginUsuario);

        if (usuarioDoBanco != null && loginUsuario.equals(usuarioDoBanco.getUsername())) {
            throw new UsuarioException("Já existe um usuário com esse login");
        }


    }


}
