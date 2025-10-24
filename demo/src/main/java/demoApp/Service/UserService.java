package demoApp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import demoApp.Dto.UsuarioDTO;
import demoApp.Entities.Usuario;
import demoApp.Repository.UsuarioRepository;
import demoApp.Validation.UsuarioValidation.ValidadorDeUsuario;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository   usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private List<ValidadorDeUsuario> validadorUsuario;


    public UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO){

        validadorUsuario.forEach(v -> v.validar(usuarioDTO));

        Usuario usuario = this.converterUsuarioDTOEmUsuario(usuarioDTO);

        String senhaCriptografada = passwordEncoder.encode(usuarioDTO.getSenha());
        usuario.setSenha(senhaCriptografada);

        usuarioRepository.save(usuario);

        return usuarioDTO;
    }


    public UsuarioDTO converterUsuarioEmUsuarioDTO(Usuario usuario){
        UsuarioDTO usuarioConvertidoDTO = new UsuarioDTO();
            usuarioConvertidoDTO.setLogin(usuario.getLogin());
            usuarioConvertidoDTO.setSenha(usuario.getSenha());
        
            return usuarioConvertidoDTO;
    }

    public Usuario converterUsuarioDTOEmUsuario(UsuarioDTO usuarioDTO){
        Usuario usuarioConvertido =  new Usuario();
        usuarioConvertido.setLogin(usuarioDTO.getLogin());
        usuarioConvertido.setSenha(usuarioDTO.getSenha());

        return usuarioConvertido;
    }

}
