package demoApp.Dto;

import demoApp.Entities.Enums.TypePhone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO{
 
    
    @NotEmpty(message = "Tipo de telefone é obrigatório")
    private TypePhone typePhone;

    @NotBlank(message = "Número é obrigatório")
    private String numero;   
        
    @NotBlank(message = "DDD é obrigatório")
    private String ddd;

    
}

