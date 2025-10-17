package demoApp.Dto;

import demoApp.Entities.Enums.Categoria;
import demoApp.Entities.Enums.StatusProduto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoSaidaDTO {

    private Long id;

    private String nome;

    private String fornecedor;

    private StatusProduto statusProduto;

    private Categoria categoria;

    private Double preco;

    private Integer quantidade;

}
