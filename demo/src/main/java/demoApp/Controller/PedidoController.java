package demoApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demoApp.Dto.PedidoEntradaDTO;
import demoApp.Dto.PedidoSaidaDTO;
import demoApp.Service.PedidoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/Pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;
    

    //vistoria
    @PostMapping("/RealizarPedido")
    public ResponseEntity<PedidoSaidaDTO> RealizarPedido(@Valid @RequestBody PedidoEntradaDTO pedidoDTO){
        PedidoSaidaDTO pedidoSaidaDTO = pedidoService.RealizarPedido(pedidoDTO);
        return ResponseEntity.ok().body(pedidoSaidaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoSaidaDTO> BuscarPedido(@Valid @PathVariable Long id){
        PedidoSaidaDTO pedidoSaidaDTO = pedidoService.buscarPedidoPorId(id);
        return ResponseEntity.ok().body(pedidoSaidaDTO);
    }

    @GetMapping("/CancelarPedido/{id}")
    public ResponseEntity<Boolean> CancelarPedido(@Valid @PathVariable Long id){
        Boolean resposta = pedidoService.cancelarPedido(id);
        return ResponseEntity.ok().body(resposta);
    }

    @GetMapping("/BuscarPedidosCliente/{id}")
    public ResponseEntity<List<PedidoSaidaDTO>> BuscarPedidosCliente(@Valid @PathVariable Long id){
        List<PedidoSaidaDTO> pedidos = pedidoService.listarPedidosPorClienteId(id);
        
        return ResponseEntity.ok().body(pedidos);
    }
    
}


