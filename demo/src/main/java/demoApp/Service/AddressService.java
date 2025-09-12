package demoApp.Service;

import org.springframework.stereotype.Service;

import demoApp.Dto.AddressDTO;
import demoApp.Entities.Address;

@Service
public class AddressService {

    public AddressDTO convertToAddressDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreet(address.getStreet());
        addressDTO.setCity(address.getCity());
        addressDTO.setUf(address.getUf());
        addressDTO.setNumber(address.getNumber());
        addressDTO.setComplemento(address.getComplemento());
        addressDTO.setCep(address.getCep());
        addressDTO.setBairro(address.getBairro());
        return addressDTO;
    }

    public Address convertDTOtoAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setUf(addressDTO.getUf());
        address.setNumber(addressDTO.getNumber());
        address.setComplemento(addressDTO.getComplemento());
        address.setCep(addressDTO.getCep());
        address.setBairro(addressDTO.getBairro());
        return address;
    }
}
