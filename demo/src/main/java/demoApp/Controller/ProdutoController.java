package demoApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demoApp.Dto.ProdutoEntradaDTO;
import demoApp.Dto.ProdutoSaidaDTO;
import demoApp.Entities.Enums.EstacaoClimatica;
import demoApp.Service.ProdutoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/Produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/Cadastrar")
    public ResponseEntity<ProdutoSaidaDTO> CadastrarProduto(@Valid @RequestBody ProdutoEntradaDTO entrada) {
        ProdutoSaidaDTO produto =  produtoService.adicionarNovoProduto(entrada);
        return ResponseEntity.ok().body(produto);

    }

    //verificar se está zerado no estoque
    @PostMapping("/Desativar/{id}")
    public ResponseEntity<Boolean> DesativarItem(@Valid @PathVariable Long id){
        Boolean resposta = produtoService.desativarproduto(id);
        return ResponseEntity.ok().body(resposta);
    }

    //Vindo produto indisponivel
    @GetMapping("/Categoria/{categoria}")
    public ResponseEntity<List<ProdutoSaidaDTO>> BuscarProdutoPorCategoria(@Valid @PathVariable String categoria){

        EstacaoClimatica categoriaEnum = EstacaoClimatica.valueOf(categoria.toUpperCase());

        List<ProdutoSaidaDTO> produto = (produtoService.buscarprodutoPorCategoria(categoriaEnum));

        return ResponseEntity.ok().body(produto);
    }

//CRIAR CONTA PARA PEGAR A CHAVE KEY
    @GetMapping("/PaginaInicial/{id}")
    public ResponseEntity<List<ProdutoSaidaDTO>> BuscarProdutoPorCategoria(@Valid @PathVariable Long id){

        List<ProdutoSaidaDTO> produtoDto = produtoService.buscarProdutoAutomatico(id);

        return ResponseEntity.ok().body(produtoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> AtualizarProduto(@Valid @RequestBody ProdutoEntradaDTO produtoDTO, @PathVariable Long id){
        produtoService.atualizarProduto(id, produtoDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/Listar")
    public ResponseEntity<List<ProdutoSaidaDTO>> ListarProdutos(){
        return ResponseEntity.ok().body(produtoService.todosProdutos());
    }

    @GetMapping("/ListarDisponivel")
    public ResponseEntity<List<ProdutoSaidaDTO>> ListarProdutosDisponivel(){

        return ResponseEntity.ok().body(produtoService.listarProdutosDisponiveis());
    }
    
    @GetMapping("/ListarIndisponivel")
    public ResponseEntity<List<ProdutoSaidaDTO>> ListarIndisponivel(){
        return ResponseEntity.ok().body(produtoService.listarProdutosIndisponivel());
    }

    @GetMapping("{id}")
    public ResponseEntity<ProdutoSaidaDTO> BuscarPorId(@PathVariable Long id){
        ProdutoSaidaDTO produto = produtoService.buscarProdutoPorId(id);
        return ResponseEntity.ok().body(produto);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<ProdutoSaidaDTO> BuscarProdutoPorNome(@PathVariable String nome){
        ProdutoSaidaDTO produto = produtoService.buscarProdutoPorNome(nome);
        return ResponseEntity.ok().body(produto);
    }

}
