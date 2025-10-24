package demoApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demoApp.Dto.UsuarioDTO;
import demoApp.Service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UserService userService;

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody@Valid UsuarioDTO usuarioDTO){

        UsuarioDTO usuarioSalvoNoBanco = userService.criarUsuario(usuarioDTO);

        return ResponseEntity.ok().body(usuarioSalvoNoBanco);
    }

}
