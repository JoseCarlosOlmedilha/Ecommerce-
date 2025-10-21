package demoApp.Dto;

import demoApp.Entities.Enums.UnidadeFederativa;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {

    @NotBlank(message = "O nome da Rua é obrigatório")
    private String rua;

    @NotNull(message = "O campo cidade precisa ser preenchido")
    private String cidade;

    @NotNull
    private UnidadeFederativa uf;
   
    @NotBlank(message = "O numero não pode ser nulo")
    @Min(0)
    private String numero;
   
    private String complemento;
   
   @NotBlank(message = "O campo CPF precisa ser preenchido")
    private String cep;
   
   @NotBlank(message = "O campo bairro deve ser preenchido")
    private String bairro;



}
