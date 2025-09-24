package demoApp.Dto;

import demoApp.Entities.Enums.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItensSaidaDTO {

    private Long id;

    private String nome;

    private String fornecedor;

    private String statusItem;

    private Categoria categoria;

    private Double preco;

    private Integer quantidade;

}
