package demoApp.Service;

import org.springframework.stereotype.Service;

import demoApp.Dto.EnderecoDTO;
import demoApp.Entities.Endereco;

@Service
public class EnderecoService {

    public EnderecoDTO converterEnderecoParaEnderecoDTO(Endereco endereco) {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setRua(endereco.getRua());
        enderecoDTO.setCidade(endereco.getCidade());
        enderecoDTO.setUf(endereco.getUf());
        enderecoDTO.setNumero(endereco.getNumeroResidencial());
        enderecoDTO.setComplemento(endereco.getComplemento());
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setBairro(endereco.getBairro());
        return enderecoDTO;
    }

    public Endereco converterDTOParaEndereco(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        endereco.setRua(enderecoDTO.getRua());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setUf(enderecoDTO.getUf());
        endereco.setNumeroResidencial(enderecoDTO.getNumero());
        endereco.setComplemento(enderecoDTO.getComplemento());
        endereco.setCep(enderecoDTO.getCep());
        endereco.setBairro(enderecoDTO.getBairro());
        return endereco;
    }
}
