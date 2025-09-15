    package demoApp.Dto;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;


import demoApp.Entities.Enums.SexoClient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    @NotEmpty
    private String nome;

    @CPF
    @NotEmpty
    private String cpf;

    @Email
    private String email;

    private SexoClient sexo;

    @Past
    private LocalDate dateOfBirth;

    private AddressDTO address;

    private List<PhoneDTO> phones;

}
