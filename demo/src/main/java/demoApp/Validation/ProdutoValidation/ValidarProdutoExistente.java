package demoApp.Validation.ProdutoValidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import demoApp.Dto.ProdutoEntradaDTO;
import demoApp.Exception.ProdutoException;
import demoApp.Repository.ProdutoRepository;

@Component
public class ValidarProdutoExistente implements ValidadorProduto{

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public void validar(ProdutoEntradaDTO produtoEntradaDTO) {      
            boolean nomeExiste = produtoRepository.existsByNome(produtoEntradaDTO.getNome());

        if (nomeExiste) {
            throw new ProdutoException("Já existe um produto com esse nome");
        }

        
    }

}
