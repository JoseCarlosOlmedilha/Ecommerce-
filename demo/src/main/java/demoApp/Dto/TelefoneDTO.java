package demoApp.Dto;

import demoApp.Entities.Enums.TipoTelefone;
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
public class TelefoneDTO{
 
    
    @NotNull(message = "Tipo de telefone é obrigatório")
    private TipoTelefone tipoTelefone;

    @NotBlank(message = "Número é obrigatório")
    private String numero;   
        
    @NotBlank(message = "DDD é obrigatório")
    private String ddd;

    
}

