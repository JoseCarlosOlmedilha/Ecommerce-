package demoApp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import demoApp.Dto.ProdutoEntradaDTO;
import demoApp.Dto.ProdutoSaidaDTO;
import demoApp.Entities.Client;
import demoApp.Entities.Produto;
import demoApp.Entities.Enums.Categoria;
import demoApp.Entities.Enums.StatusProduto;
import demoApp.Exception.ProdutoException;
import demoApp.Repository.ClientRepository;
import demoApp.Repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private WeatherService weatherService;

    public ProdutoSaidaDTO AdicionarNovoProduto(ProdutoEntradaDTO produtoEntradaDTO){
        if(produtoRepository.existsByNome(produtoEntradaDTO.getNome())){
                throw new ProdutoException("Produto já existe, codigo do produto:" +
                                produtoRepository.findByNome(produtoEntradaDTO.getNome()).getId());
        }

        Produto produto = ConverterDtoParaEntidade(produtoEntradaDTO);
        produtoRepository.save(produto);

        ProdutoSaidaDTO produtoSaidaDto = ConverterEntidadeParaDto(produto);
        return produtoSaidaDto;
    }

    public Boolean Desativarproduto(Long id){

        Produto produto = produtoRepository.findById(id).orElseThrow(() -> 
            new ProdutoException("produto não encontrado")
        );

        produto.setStatusProduto(StatusProduto.INDISPONIVEL);
        produtoRepository.save(produto);

        return true;
    } 

    public List<ProdutoSaidaDTO> buscarProdutoAutomatico(Long id){

        Client client = clientRepository.findById(id).orElseThrow(()
                     -> new RuntimeException("Cliente não encontrado"));;

        Categoria categoria = obterCategoriaPorCidade(client.getAddress().getCity());


        List<Produto> produto = produtoRepository.findByCategoria(categoria);
       
        List<ProdutoSaidaDTO> produtoDto = produto.stream()
            .map(this::ConverterEntidadeParaDto)
            .toList();
        return produtoDto;
       
    }


    public List<ProdutoSaidaDTO> BuscarprodutoPorCategoria(Categoria categoria){

        List<Produto> produto = produtoRepository.findByCategoria(categoria);
       
        List<ProdutoSaidaDTO> produtoDto = produto.stream()
            .map(this::ConverterEntidadeParaDto)
            .toList();
        return produtoDto;
       
    }

    public Categoria obterCategoriaPorCidade(String cidade) {
        Double temperatura = weatherService.getTemperatureByCity(cidade);
        return ClassificarTemperatura(temperatura); 

    }

    public Categoria ClassificarTemperatura(Double temperatura){
        if (temperatura <= 17) {
            return Categoria.INVERNO;
        } else if (temperatura <= 24) {
            return Categoria.PRIMAVERA;
        } else {
            return Categoria.VERAO;
        }

    }
    

    public Double PegarTemperatura(String cidade){
            
        Double temperatura = weatherService.getTemperatureByCity(cidade);

        return temperatura;
    }

    public void AtualizarProduto(Long id, ProdutoEntradaDTO produtoEntradaDTO){
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> 
            new ProdutoException("produto não encontrado")
        );
        produto.setNome(produtoEntradaDTO.getNome());
        produto.setCategoria(produtoEntradaDTO.getCategoria());
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

    public List<ProdutoSaidaDTO> TodosProdutos(){
        List<Produto> produtos = produtoRepository.findAll();

        List<ProdutoSaidaDTO> produtosDto = produtos.stream()
            .map(this::ConverterEntidadeParaDto)
            .toList();
        return produtosDto;
    }

    public List<ProdutoSaidaDTO> ListarProdutosDisponiveis(){
        List<Produto> produtos = produtoRepository.findByStatusProduto(StatusProduto.DISPONIVEL);
        List<ProdutoSaidaDTO> produtosDto = produtos.stream()
            .map(this::ConverterEntidadeParaDto)
            .toList();
        return produtosDto;
    }

    public List<ProdutoSaidaDTO> ListarProdutosIndisponivel(){
        List<Produto> Produtos = produtoRepository.findByStatusProduto(StatusProduto.INDISPONIVEL);
        List<ProdutoSaidaDTO> produtosDto = Produtos.stream()
            .map(this::ConverterEntidadeParaDto)
            .toList();
        return produtosDto;
    }

    public ProdutoSaidaDTO BuscarProdutoPorId(Long id){
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> 
            new ProdutoException("produto não encontrado")
        );
        ProdutoSaidaDTO produtoDto = ConverterEntidadeParaDto(produto);
        return produtoDto;

    }

    public Produto buscarProdutoEntidadePorId(Long id){
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> 
            new ProdutoException("produto não encontrado")
        );
        return produto;

    }

    public ProdutoSaidaDTO BuscarProdutoPorNome(String nome){
        Produto produto = produtoRepository.findByNome(nome);
        if(produto == null){
            throw new ProdutoException("produto não encontrado");
        } 

        ProdutoSaidaDTO produtoDto = ConverterEntidadeParaDto(produto);
        return produtoDto;
    }

    public Produto ConverterDtoParaEntidade(ProdutoEntradaDTO produtoEntradaDTO){
        Produto produto = new Produto();
        produto.setNome(produtoEntradaDTO.getNome());
        produto.setCategoria(produtoEntradaDTO.getCategoria());
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

    public ProdutoSaidaDTO ConverterEntidadeParaDto(Produto Produtos){
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
