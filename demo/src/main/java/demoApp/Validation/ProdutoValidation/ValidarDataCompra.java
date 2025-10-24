package demoApp.Validation.ProdutoValidation;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Component;

import demoApp.Dto.ProdutoEntradaDTO;
import demoApp.Exception.ProdutoException;

@Component
public class ValidarDataCompra implements ValidadorProduto{

    @Override
    public void validar(ProdutoEntradaDTO produtoEntradaDTO) {
      
        LocalDate dataCompra = produtoEntradaDTO.getDataCompra();
        LocalDate dataAtual = LocalDate.now();

        Period periodo = Period.between(dataCompra, dataAtual);

        if(periodo.getMonths() > 1){
            throw new ProdutoException("Não é possivel cadastrar esse produto, pois a data de compra é maior que 2 meses");
        }
    }    

}
