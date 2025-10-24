package demoApp.Validation.ClienteValidation;

import org.springframework.stereotype.Component;

import demoApp.Dto.ClienteDTO;

@Component
public class ValidadorNomeCliente implements ValidadorCliente{

    @Override
    public void validar(ClienteDTO clienteDTO) {

        throw new UnsupportedOperationException("Unimplemented method 'validar'");
    }

}
