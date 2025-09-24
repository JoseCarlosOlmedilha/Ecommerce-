    package demoApp.Dto;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;


import demoApp.Entities.Enums.SexoClient;
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
public class ClientDTO {

    @NotBlank(message = "Nome é um campo obrigatório")
    @Size(min = 3, max = 100, message = "O campo nome deve ter pelo manoes 5 letras e no maximo 50")
    private String nome;

    @NotBlank(message = "Cpf é um campo obrigatório")
    @CPF(message = "Formato de cpf invalido")
    private String cpf;

    @NotBlank(message = "E-mail é obrigaroio")
    @Email(message = "Formato de E-mail Invalido")
    private String email;

    @NotNull(message = "Sexo é obrigatório")
    private SexoClient sexo;

    @Past(message = "Data de lançamento deve estar no pasado")
    @NotNull(message = "Data de aniversario é obrigatória")
    private LocalDate dateOfBirth;

    @Valid
    @NotNull(message = "Endereço é obrigatório")
    private AddressDTO address;

    @Valid
    @NotEmpty(message = "Você deve cadastrar pelo menos 1 endereço")
    private List<PhoneDTO> phones;

}
