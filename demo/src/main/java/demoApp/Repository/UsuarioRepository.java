package demoApp.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import demoApp.Entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

  UserDetails findByLogin(String login);




    
} 

