package demoApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demoApp.Dto.PedidoDTO;
import demoApp.Entities.Pedido;
import demoApp.Entities.Enums.StatusPedido;
import demoApp.Repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public void GuardarPedido(PedidoDTO pedidoDTO){

    }

    public void TrocarStatusPedido(PedidoDTO pedidoDTO, StatusPedido novoStatus){

    }

    public void CancelarPedido(PedidoDTO prdidoDTO){

    }

    public void AplicarDescontoPedido(PedidoDTO pedidoDTO, Double valorDesconto){

    }

    public void AtualizarPedido(PedidoDTO pedidoDTO, Long id){

    }

    public void BuscarPedido(Long id){

    }

    public void ListarPedidoPorCliente(long id){

    }

     public void ListarTodosPedidos(){

    }

    public void converterDtOParaEntidade(PedidoDTO pedidoDTO){


    }

    public void converterEntidadeParaDTO(Pedido pedido){

    }


    
}
