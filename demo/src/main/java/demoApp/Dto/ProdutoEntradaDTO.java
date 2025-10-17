package demoApp.Dto;

import java.time.LocalDate;

import demoApp.Entities.Enums.Categoria;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ProdutoEntradaDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Fornecedor é obrigatório")
    private String fornecedor;

    @NotNull(message = "Categoria é obrigatório")
    private Categoria categoria;

    @NotNull(message = "Preço é obrigatório")
    @Positive(message = "Preço deve ser maior que zero")
    private Double preco;

    @NotNull(message = "Quantidade é obrigatório")
    @Min(value = 1, message = "Quantidade deve ser maior que zero")
    private Integer quantidade;

    @NotNull(message = "Data de compra é obrigatório")
    @Past(message = "Data de compra deve estar no passado")
    private LocalDate dataCompra;
}
