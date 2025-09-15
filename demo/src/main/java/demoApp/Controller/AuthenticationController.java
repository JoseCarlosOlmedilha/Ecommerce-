package demoApp.Controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demoApp.Config.Security.TokenService;
import demoApp.Dto.AuthenticationDto;
import demoApp.Dto.tokenJWTDTO;
import demoApp.Entities.User;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid AuthenticationDto authDto){

        var passwordEncoder = new BCryptPasswordEncoder();
        String senhaEncriptada = passwordEncoder.encode(authDto.senha());
        System.out.println(senhaEncriptada);

        var autenticationToken =  new UsernamePasswordAuthenticationToken(authDto.login(), authDto.senha());
        var authentication = manager.authenticate(autenticationToken);

        var tokenJWT = tokenService.gerarToken((User)authentication.getPrincipal());

        return ResponseEntity.ok(new tokenJWTDTO(tokenJWT));

    }
    
    

}
