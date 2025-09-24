package demoApp.Dto;

import java.time.LocalDate;

import demoApp.Entities.Enums.Categoria;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItensEntradaDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Fornecedor é obrigatório")
    private String fornecedor;

    @NotEmpty(message = "Categoria é obrigatório")
    private Categoria categoria;

    @NotBlank(message = "Preço é obrigatório")
    @Positive(message = "Preço deve ser maior que zero")
    private Double preco;

    @NotBlank(message = "Quantidade é obrigatório")
    @Min(value = 1, message = "Quantidade deve ser maior que zero")
    private Integer quantidade;

    @NotBlank(message = "Data de compra é obrigatório")
    @Past(message = "Data de compra deve estar no passado")
    private LocalDate dataCompra;

}
