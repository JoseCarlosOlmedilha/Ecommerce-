package demoApp.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demoApp.Dto.ItensPedidoDTO;
import demoApp.Dto.PedidoDTO;
import demoApp.Entities.ItensPedido;
import java.util.stream.Collectors;             
import demoApp.Entities.Pedido;
import demoApp.Entities.Enums.StatusPedido;
import demoApp.Repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClientService ClientService;


    public Boolean RealizarPedido(PedidoDTO pedidoDTO){
        Pedido pedido = converterDtOParaEntidade(pedidoDTO);
        pedido.setStatusPedido(StatusPedido.REALIZADO);
        if(pedidoDTO.getDesconto() != null){
            pedido.setValorTotal(AplicarDescontoPedido(pedidoDTO, pedidoDTO.getDesconto()));
        }
        pedidoRepository.save(pedido);
        return true;
    }

    public void TrocarStatusPedido(PedidoDTO pedidoDTO, StatusPedido novoStatus){

    }

    public void CancelarPedido(PedidoDTO pedidoDTO){
        pedidoDTO.setStatusPedido(StatusPedido.CANCELADO);
        AtualizarPedido(pedidoDTO,  pedidoDTO.getId());
    }

    public Double AplicarDescontoPedido(PedidoDTO pedidoDTO, Double valorDesconto){
        Double valor  = pedidoDTO.getValorTotal() - valorDesconto;
        return valor;
    }

    public void AtualizarPedido(PedidoDTO pedidoDTO, Long id){

    }

    public PedidoDTO BuscarPedido(Long id){
        return converterEntidadeParaDTO(pedidoRepository.findById(id).get());
    }

    public List<PedidoDTO> ListarPedidoPorCliente(Long id){
        List<Pedido> pedidos = pedidoRepository.findPedidoByClients(id);
        return pedidos.stream().map(this::converterEntidadeParaDTO).collect(Collectors.toList());

    }

    public List<Pedido> ListarTodosPedidos(){
        return pedidoRepository.findAll();
    }

    public Double CalcularValorTotal(List<ItensPedidoDTO> itensPedidoDTO){

        Double valorTotal = 0.0;
        for (ItensPedidoDTO item : itensPedidoDTO) {
            valorTotal += item.getProdutos().getPreco() * item.getProdutos().getQuantidade();
        }
        return valorTotal;
    }

    public LocalDate  CalcularEntrega(LocalDate dataPedido, Integer Dias){
        return dataPedido.plusDays(Dias);

    }

    public Pedido converterDtOParaEntidade(PedidoDTO pedidoDTO){
      Pedido pedido = new Pedido();
      pedido.setCliente(ClientService.dtoParaClient(pedidoDTO.getCliente()));
      pedido.setDataPedido(LocalDateTime.now());
      pedido.setDataEntrega(CalcularEntrega(LocalDate.now(), 7));
      pedido.setValorTotal(CalcularValorTotal(pedidoDTO.getItensPedidos()));
      pedido.setItensPedidos(pedidoDTO.getItensPedidos().stream()
            .map(dto -> { ItensPedido itensPedido = new ItensPedido();
            itensPedido.setProdutos(dto.getProdutos());
            itensPedido.setPedido(pedido);
            return itensPedido;
            })
            .collect(Collectors.toList())
        );
      return pedido;

    }

    public PedidoDTO converterEntidadeParaDTO(Pedido pedido){
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(pedido.getId());
        pedidoDTO.setCliente(ClientService.clientToDTO(pedido.getCliente()));
        pedidoDTO.setDataPedido(pedido.getDataPedido());
        pedidoDTO.setDataEntrega(pedido.getDataEntrega());
        pedidoDTO.setValorTotal(pedido.getValorTotal());
        pedidoDTO.setStatusPedido(pedido.getStatusPedido());
        pedidoDTO.setItensPedidos(pedido.getItensPedidos().stream()
            .map(item -> { ItensPedidoDTO itensPedidoDTO = new ItensPedidoDTO();
            itensPedidoDTO.setProdutos(item.getProdutos());
            itensPedidoDTO.setPedido(item.getPedido());
            return itensPedidoDTO;
            })
            .collect(Collectors.toList())
        );
        return pedidoDTO;   

    }


    
}
