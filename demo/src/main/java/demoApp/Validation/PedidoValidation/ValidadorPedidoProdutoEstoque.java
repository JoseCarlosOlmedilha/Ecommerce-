package demoApp.Validation.PedidoValidation;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import demoApp.Dto.PedidoEntradaDTO;
import demoApp.Dto.ProdutoPedidoDTO;
import demoApp.Exception.ProdutoException;
import demoApp.Repository.ProdutoRepository;

@Component
public class ValidadorPedidoProdutoEstoque implements ValidadorPedido{

    private ProdutoRepository produtoRepository;

    @Override
    public void validar(PedidoEntradaDTO pedidoEntradaDTO) {
        List<ProdutoPedidoDTO> produtoPedido = pedidoEntradaDTO.getProdutoPedidos();
        
       List<Long> produtosIds = produtoPedido.stream()
        .map(ProdutoPedidoDTO::getProdutos_id) // método com parênteses ou method reference
        .collect(Collectors.toList());

        for (Long produtoId : produtosIds) {
        Optional<Integer> quantidadeOpt = produtoRepository.findByProdutoQuantidade(produtoId);

            if (quantidadeOpt.isEmpty() || quantidadeOpt.get() <= 0) {
                throw new ProdutoException("Produto com ID " + produtoId + " está sem estoque ou não existe.");
            }
        }

    }

}
