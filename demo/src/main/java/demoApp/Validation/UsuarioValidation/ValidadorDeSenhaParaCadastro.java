package demoApp.Validation.UsuarioValidation;

import org.springframework.stereotype.Component;

import demoApp.Dto.UsuarioDTO;
import demoApp.Exception.UsuarioException;

@Component
public class ValidadorDeSenhaParaCadastro implements ValidadorDeUsuario {

    @Override
    public void validar(UsuarioDTO usuarioDTO) {

        String senhaUsuario = usuarioDTO.getSenha();

        if(senhaUsuario.length() < 5){
            throw new UsuarioException("Senha muito curta, coloque mais de 5 caracteres");
        }

    }    
}


