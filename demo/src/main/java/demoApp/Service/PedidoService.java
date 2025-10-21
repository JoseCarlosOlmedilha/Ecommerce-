package demoApp.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demoApp.Dto.PedidoEntradaDTO;
import demoApp.Dto.PedidoSaidaDTO;
import demoApp.Dto.ProdutoPedidoDTO;

import java.util.stream.Collectors;             
import demoApp.Entities.Pedido;
import demoApp.Entities.Produto;
import demoApp.Entities.ProdutoPedido;
import demoApp.Entities.Enums.StatusPedido;
import demoApp.Entities.Enums.StatusProduto;
import demoApp.Repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService; 

    public PedidoSaidaDTO RealizarPedido(PedidoEntradaDTO pedidoDTO) {

        Pedido pedido = converterDtoParaEntidade(pedidoDTO);
        pedido.setStatusPedido(StatusPedido.REALIZADO);

        pedido.getProdutoPedidos().forEach(produtoCapturado -> {
            Produto produto = produtoCapturado.getProduto();
            if (produto.getStatusProduto() != StatusProduto.DISPONIVEL) {
                throw new RuntimeException("Produto " + produto.getNome() + " não está disponível");
            }
        });

        if (pedidoDTO.getDesconto() != null) {
            pedido.setValorTotal(aplicarDescontoPedido(pedido, pedidoDTO.getDesconto()));
        }

        pedidoRepository.save(pedido);

        return converterEntidadeParaDTO(pedido);
    }

    public Double aplicarDescontoPedido(Pedido pedido, Double valorDesconto) {
        Double valor = pedido.getValorTotal() - valorDesconto;
        return valor < 0 ? 0 : valor; 
    }

    public LocalDate calcularEntrega(LocalDate dataPedido, Integer dias) {
        return dataPedido.plusDays(dias);
    }

    public Boolean cancelarPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        if (pedido.getStatusPedido() == StatusPedido.CANCELADO) {
            return false; 
        }
        pedido.setStatusPedido(StatusPedido.CANCELADO);
        pedidoRepository.save(pedido);
        return true;
    }

    public PedidoSaidaDTO buscarPedidoPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        return converterEntidadeParaDTO(pedido);
    }

    public List<PedidoSaidaDTO> listarTodosPedidos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos.stream()
                .map(this::converterEntidadeParaDTO)
                .collect(Collectors.toList());
    }

    public List<PedidoSaidaDTO> listarPedidosPorClienteId(Long clienteId) {
        List<Pedido> pedidos = pedidoRepository.findByClienteId(clienteId);
        return pedidos.stream()
                .map(this::converterEntidadeParaDTO)
                .collect(Collectors.toList());
    }

    public Pedido converterDtoParaEntidade(PedidoEntradaDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setCliente(clienteService.converterDTOParaCliente(pedidoDTO.getCliente()));
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setDataEntrega(calcularEntrega(LocalDate.now(), 7));

        List<ProdutoPedido> produtosPedido = pedidoDTO.getProdutoPedidos().stream()
            .map(dto -> {
                ProdutoPedido itemPedido = new ProdutoPedido();
                itemPedido.setPedido(pedido);
                itemPedido.setQuantidade(dto.getQuantidade());

                // Buscar produto pelo id
                Produto produto = produtoService.buscarProdutoEntidadePorId(dto.getProdutos_id());
                itemPedido.setProduto(produto);

                return itemPedido;
            })
            .collect(Collectors.toList());

        pedido.setProdutoPedidos(produtosPedido);

        // Calcula valor total
        Double valorTotal = produtosPedido.stream()
            .mapToDouble(i -> i.getProduto().getPreco() * i.getQuantidade())
            .sum();

        pedido.setValorTotal(valorTotal);

        return pedido;
    }

    public PedidoSaidaDTO converterEntidadeParaDTO(Pedido pedido) {
        PedidoSaidaDTO dto = new PedidoSaidaDTO();
        dto.setId(pedido.getPedidoId());
        dto.setCliente(clienteService.converterClienteParaDto(pedido.getCliente()));
        dto.setDataPedido(pedido.getDataPedido());
        dto.setDataEntrega(pedido.getDataEntrega());
        dto.setValorTotal(pedido.getValorTotal());
        dto.setStatusPedido(pedido.getStatusPedido());

        dto.setProdutoPedidos(
            pedido.getProdutoPedidos().stream()
                .map(pp -> {
                    ProdutoPedidoDTO produtoPedido = new ProdutoPedidoDTO();
                    produtoPedido.setProdutos_id(pp.getProduto().getId());
                    produtoPedido.setQuantidade(pp.getQuantidade());
                    return produtoPedido; // <- Aqui estava faltando
                })
                .collect(Collectors.toList()) // <- Aqui fecha certinho o map
        );

        return dto;
    }

}
