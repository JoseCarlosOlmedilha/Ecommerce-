package demoApp.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    @NotBlank(message = "Login não pode ser nulo, vazio ou conter apenas espaços!")
    private String login;

    @NotBlank(message = "Senha não pode ser nulo, vazio ou conter apenas espaços!")
    private String senha;

}
