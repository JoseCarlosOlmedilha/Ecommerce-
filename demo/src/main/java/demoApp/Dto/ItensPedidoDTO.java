package demoApp.Dto;

import demoApp.Entities.Itens;
import demoApp.Entities.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItensPedidoDTO {


    private Long id;
    
    private Itens produtos;

    private Pedido pedido;

}
