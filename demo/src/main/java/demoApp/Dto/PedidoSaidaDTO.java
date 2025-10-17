package demoApp.Dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import demoApp.Entities.Enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoSaidaDTO {

    private Long id;

    private ClientDTO cliente;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataPedido;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataEntrega;

    private Double valorTotal;

    private StatusPedido statusPedido;

    private List<ProdutoPedidoDTO> ProdutoPedidos;

}
