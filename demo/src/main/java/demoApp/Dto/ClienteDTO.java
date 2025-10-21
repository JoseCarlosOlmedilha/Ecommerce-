    package demoApp.Dto;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;


import demoApp.Entities.Enums.Sexo;
import demoApp.Entities.Enums.StatusCliente;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    @NotBlank(message = "Nome é um campo obrigatório")
    @Size(min = 3, max = 200, message = "Nome muito curto")
    private String nome;

    @NotBlank(message = "Cpf é um campo obrigatório")
    @CPF(message = "Formato de cpf invalido")
    private String cpf;

    @Enumerated(EnumType.STRING)
    private StatusCliente statusCliente;

    @NotBlank(message = "E-mail é obrigaroio")
    @Email(message = "Formato de E-mail Invalido")
    private String email;

    @NotNull(message = "Sexo é obrigatório")
    private Sexo sexo;

    @Past(message = "Data de lançamento deve estar no pasado")
    @NotNull(message = "Data de aniversario é obrigatória")
    private LocalDate dataAniversario;

    @Valid
    @NotNull(message = "Endereço é obrigatório")
    private EnderecoDTO endereco;

    @Valid
    @NotEmpty(message = "Você deve cadastrar pelo menos 1 endereço")
    private List<TelefoneDTO> telefone;

}
