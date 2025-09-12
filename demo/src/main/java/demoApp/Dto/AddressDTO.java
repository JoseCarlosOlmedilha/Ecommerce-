package demoApp.Dto;

import demoApp.Entities.Enums.UfAddress;
import lombok.Data;

@Data
public class AddressDTO {

    private String street;
    private String city;
    private UfAddress uf;
    private String number;
    private String complemento;
    private String cep;
    private String bairro;

}
