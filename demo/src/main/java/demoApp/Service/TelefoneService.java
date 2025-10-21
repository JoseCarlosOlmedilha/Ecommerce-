package demoApp.Service;

import org.springframework.stereotype.Service;

import demoApp.Dto.TelefoneDTO;
import demoApp.Entities.Telefone;

@Service
public class TelefoneService {

    public TelefoneDTO converterToTelefoneDTO(Telefone telefone) {
        TelefoneDTO TelefoneDTO = new TelefoneDTO();
        TelefoneDTO.setNumero(telefone.getNumero());
        TelefoneDTO.setDdd(telefone.getDdd());
        TelefoneDTO.setTipoTelefone(telefone.getTipoTelefone());
        return TelefoneDTO;
    }

    public Telefone converterDTOToTelefone(TelefoneDTO telefoneDTO) {
        Telefone telefone = new Telefone();
        telefone.setNumero(telefoneDTO.getNumero());
        telefone.setDdd(telefoneDTO.getDdd());
        telefone.setTipoTelefone(telefoneDTO.getTipoTelefone());
        return telefone;
    }


}
