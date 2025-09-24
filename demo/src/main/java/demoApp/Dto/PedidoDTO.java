package demoApp.Dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import demoApp.Entities.ItensPedido;
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

    private ClientDTO cliente;

    private LocalDateTime dataPedido;

    private LocalDate dataEntrega;

    private Double valorTotal;

    private StatusPedido statusPedido;

    private List<ItensPedido> itensPedidos;



}
