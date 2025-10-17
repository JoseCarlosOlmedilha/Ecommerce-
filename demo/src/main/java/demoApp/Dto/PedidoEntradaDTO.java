package demoApp.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoEntradaDTO {

    private ClientDTO cliente;

    private Double desconto;

    private List<ProdutoPedidoDTO> ProdutoPedidos;



}
