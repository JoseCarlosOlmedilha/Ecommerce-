package demoApp.Service;

import org.springframework.stereotype.Service;

import demoApp.Dto.PhoneDTO;
import demoApp.Entities.Phone;

@Service
public class PhoneService {

    public PhoneDTO convertToPhoneDTO(Phone phone) {
        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setNumero(phone.getNumero());
        phoneDTO.setDdd(phone.getDdd());
        phoneDTO.setTypePhone(phone.getTypePhone());
        return phoneDTO;
    }

    public Phone convertDTOToPhone(PhoneDTO phoneDTO) {
        Phone phone = new Phone();
        phone.setNumero(phoneDTO.getNumero());
        phone.setDdd(phoneDTO.getDdd());
        phone.setTypePhone(phoneDTO.getTypePhone());
        return phone;
    }


}
