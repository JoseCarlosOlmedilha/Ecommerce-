package demoApp.Dto;

import java.time.LocalDate;
import java.util.List;

import demoApp.Entities.Enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    private Long id;

    private ClienteDTO cliente;

    private LocalDate dataPedido;

    private LocalDate dataEntrega;

    private Double valorTotal;

    private StatusPedido statusPedido;

    private Double desconto;

    private List<ProdutoPedidoDTO> ProdutoPedidos;
    

}
