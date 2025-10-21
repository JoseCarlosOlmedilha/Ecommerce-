package demoApp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import demoApp.Dto.ProdutoEntradaDTO;
import demoApp.Dto.ProdutoSaidaDTO;
import demoApp.Entities.Cliente;
import demoApp.Entities.Produto;
import demoApp.Entities.Enums.EstacaoClimatica;
import demoApp.Entities.Enums.StatusProduto;
import demoApp.Exception.ProdutoException;
import demoApp.Repository.ClienteRepository;
import demoApp.Repository.ProdutoRepository;
import demoApp.Validation.ProdutoValidation.ValidadorProduto;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    List<ValidadorProduto> validadorProdutos;

    public ProdutoSaidaDTO adicionarNovoProduto(ProdutoEntradaDTO produtoEntradaDTO){
     
        validadorProdutos.forEach(v -> v.validar(produtoEntradaDTO));

        Produto produto = converterDtoParaEntidade(produtoEntradaDTO);
        produtoRepository.save(produto);

        

        ProdutoSaidaDTO produtoSaidaDto = converterEntidadeParaDto(produto);
        return produtoSaidaDto;
    }

    public Boolean desativarproduto(Long id){

        Produto produto = produtoRepository.findById(id).orElseThrow(() -> 
            new ProdutoException("produto não encontrado")
        );

        produto.setStatusProduto(StatusProduto.INDISPONIVEL);
        produtoRepository.save(produto);

        return true;
    } 

    public List<ProdutoSaidaDTO> buscarProdutoAutomatico(Long id){

        Cliente cliente = clienteRepository.findById(id).orElseThrow(()
                     -> new RuntimeException("Cliente não encontrado"));;

        EstacaoClimatica categoria = obterCategoriaPorCidade(cliente.getEndereco().getCidade());


        List<Produto> produto = produtoRepository.findByCategoria(categoria);
       
        List<ProdutoSaidaDTO> produtoDto = produto.stream()
            .map(this::converterEntidadeParaDto)
            .toList();
        return produtoDto;
       
    }

    public List<ProdutoSaidaDTO> buscarprodutoPorCategoria(EstacaoClimatica categoria){

        List<Produto> produto = produtoRepository.findByCategoria(categoria);
       
        List<ProdutoSaidaDTO> produtoDto = produto.stream()
            .map(this::converterEntidadeParaDto)
            .toList();
        return produtoDto;
       
    }

    public EstacaoClimatica obterCategoriaPorCidade(String cidade) {
        Double temperatura = weatherService.getTemperatureByCity(cidade);
        return classificarTemperatura(temperatura); 

    }

    public EstacaoClimatica classificarTemperatura(Double temperatura){
        if (temperatura <= 17) {
            return EstacaoClimatica.INVERNO;
        } else if (temperatura <= 24) {
            return EstacaoClimatica.PRIMAVERA;
        } else {
            return EstacaoClimatica.VERAO;
        }

    }

    public Double pegarTemperatura(String cidade){
            
        Double temperatura = weatherService.getTemperatureByCity(cidade);

        return temperatura;
    }

    public void atualizarProduto(Long id, ProdutoEntradaDTO produtoEntradaDTO){
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> 
            new ProdutoException("produto não encontrado")
        );
        produto.setNome(produtoEntradaDTO.getNome());
        produto.setCategoria(produtoEntradaDTO.getEstacao());
        produto.setFornecedor(produtoEntradaDTO.getFornecedor());
        produto.setDataCompra(produtoEntradaDTO.getDataCompra());
        produto.setPreco(produtoEntradaDTO.getPreco());
        produto.setQuantidade(produtoEntradaDTO.getQuantidade());
        if(produtoEntradaDTO.getQuantidade() > 0){
            produto.setStatusProduto(StatusProduto.DISPONIVEL);
        } else {
            produto.setStatusProduto(StatusProduto.INDISPONIVEL);
        }
        produtoRepository.save(produto);

    }

    public List<ProdutoSaidaDTO> todosProdutos(){
        List<Produto> produtos = produtoRepository.findAll();

        List<ProdutoSaidaDTO> produtosDto = produtos.stream()
            .map(this::converterEntidadeParaDto)
            .toList();
        return produtosDto;
    }

    public List<ProdutoSaidaDTO> listarProdutosDisponiveis(){
        List<Produto> produtos = produtoRepository.findByStatusProduto(StatusProduto.DISPONIVEL);
        List<ProdutoSaidaDTO> produtosDto = produtos.stream()
            .map(this::converterEntidadeParaDto)
            .toList();
        return produtosDto;
    }

    public List<ProdutoSaidaDTO> listarProdutosIndisponivel(){
        List<Produto> Produtos = produtoRepository.findByStatusProduto(StatusProduto.INDISPONIVEL);
        List<ProdutoSaidaDTO> produtosDto = Produtos.stream()
            .map(this::converterEntidadeParaDto)
            .toList();
        return produtosDto;
    }

    public ProdutoSaidaDTO buscarProdutoPorId(Long id){
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> 
            new ProdutoException("produto não encontrado")
        );
        ProdutoSaidaDTO produtoDto = converterEntidadeParaDto(produto);
        return produtoDto;

    }

    public Produto buscarProdutoEntidadePorId(Long id){
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> 
            new ProdutoException("produto não encontrado")
        );
        return produto;

    }

    public ProdutoSaidaDTO buscarProdutoPorNome(String nome){
        Produto produto = produtoRepository.findByNome(nome);
        if(produto == null){
            throw new ProdutoException("produto não encontrado");
        } 

        ProdutoSaidaDTO produtoDto = converterEntidadeParaDto(produto);
        return produtoDto;
    }

    public Produto converterDtoParaEntidade(ProdutoEntradaDTO produtoEntradaDTO){
        Produto produto = new Produto();
        produto.setNome(produtoEntradaDTO.getNome());
        produto.setCategoria(produtoEntradaDTO.getEstacao());
        produto.setFornecedor(produtoEntradaDTO.getFornecedor());
        produto.setDataCompra(produtoEntradaDTO.getDataCompra());
        produto.setPreco(produtoEntradaDTO.getPreco());
        produto.setQuantidade(produtoEntradaDTO.getQuantidade());
        if(produtoEntradaDTO.getQuantidade() > 0){
            produto.setStatusProduto(StatusProduto.DISPONIVEL);
        } else {
            produto.setStatusProduto(StatusProduto.INDISPONIVEL);
        }
        
        return produto;
    }

    public ProdutoSaidaDTO converterEntidadeParaDto(Produto Produtos){
        ProdutoSaidaDTO produtoDto = new ProdutoSaidaDTO();
        produtoDto.setNome(Produtos.getNome());
        produtoDto.setCategoria(Produtos.getCategoria());
        produtoDto.setFornecedor(Produtos.getFornecedor());
        produtoDto.setStatusProduto(Produtos.getStatusProduto());
        produtoDto.setPreco(Produtos.getPreco());
        produtoDto.setQuantidade(Produtos.getQuantidade());
        produtoDto.setId(Produtos.getId());
        
        return produtoDto;
    }


}
