package demoApp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import demoApp.Dto.ItensEntradaDTO;
import demoApp.Dto.ItensSaidaDTO;
import demoApp.Entities.Itens;
import demoApp.Entities.Enums.StatusItem;
import demoApp.Repository.ItensRepository;

@Service
public class ItensService {

    @Autowired
    private ItensRepository itensRepository;

    public void AdicionarNovoItem(ItensEntradaDTO itensDTO){
        Itens item = ConverterDtoParaEntidade(itensDTO);
        itensRepository.save(item);
    }

    public void DesativarItem(Long id){

        Itens item = itensRepository.findById(id).orElseThrow(() -> new RuntimeException("Item não encontrado"));
        item.setStatusItem(StatusItem.INDISPONIVEL);
        itensRepository.save(item);
    } 

    public List<Itens> BuscarItemPorCategoria(StatusItem status){
        List<Itens> itens = itensRepository.findByCategoria(status);
        return itens;
    }

    public void BuscarPorTemperatura(){

    }
    

    public void PegarTemperatura(){
        //chamar a API
    }

    public void AtualizarItem(Long id){

    }

    public List<Itens> TodosItens(){
        List<Itens> itens = itensRepository.findAll();
        return itens;
    }

    public List<Itens> ListarItensAtivos(){
        List<Itens> itens = itensRepository.findByStatusItem(StatusItem.DISPONIVEL);
        return itens;
    }

    public List<Itens> ListarItensInativos(){
        List<Itens> itens = itensRepository.findByStatusItem(StatusItem.INDISPONIVEL);
        return itens;
    }

    public ItensSaidaDTO BuscarItemPorId(Long id){
        Itens item = itensRepository.findById(id).orElseThrow(() -> new RuntimeException("Item não encontrado"));
        ItensSaidaDTO itemDto = ConverterEntidadeParaDto(item);
        return itemDto;

    }

    public Itens BuscarItemPorNome(String nome){
        Itens item = itensRepository.findByNome(nome);
        if(item == null){
            throw new RuntimeException("Item não encontrado");
        } 
        return item;
    }

    public Itens ConverterDtoParaEntidade(ItensEntradaDTO itensDTO){
        Itens item = new Itens();
        item.setNome(itensDTO.getNome());
        item.setCategoria(itensDTO.getCategoria());
        item.setFornecedor(itensDTO.getFornecedor());
        item.setDataCompra(itensDTO.getDataCompra());
        item.setPreco(itensDTO.getPreco());
        item.setQuantidade(itensDTO.getQuantidade());
        if(itensDTO.getQuantidade() > 0){
            item.setStatusItem(StatusItem.DISPONIVEL);
        } else {
            item.setStatusItem(StatusItem.INDISPONIVEL);
        }
        
        return item;
    }

    public ItensSaidaDTO ConverterEntidadeParaDto(Itens itens){
        ItensSaidaDTO itemDto = new ItensSaidaDTO();
        itemDto.setNome(itens.getNome());
        itemDto.setCategoria(itens.getCategoria());
        itemDto.setFornecedor(itens.getFornecedor());
        itemDto.setStatusItem(itens.getStatusItem().toString());
        itemDto.setPreco(itens.getPreco());
        itemDto.setQuantidade(itens.getQuantidade());
        itemDto.setId(itens.getId());
        
        return itemDto;
    }


}
