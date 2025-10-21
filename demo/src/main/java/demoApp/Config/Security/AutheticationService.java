package demoApp.Config.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import demoApp.Repository.UsuarioRepository;

@Service
public class AutheticationService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    
@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails usuario =  usuarioRepository.findByLogin(username);
        return usuario;
    }


}
