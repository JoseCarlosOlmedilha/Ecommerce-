package demoApp.Validation.ClienteValidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import demoApp.Dto.ClienteDTO;
import demoApp.Exception.ClienteException;
import demoApp.Repository.ClienteRepository;



@Component
public class ValidarCPFCliente implements ValidadorCliente {

    @Autowired
    private ClienteRepository clienteRepository;

    
    public void validar(ClienteDTO clienteDTO){

        boolean cpfJaCadastrado = clienteRepository.findByCpf(clienteDTO.getCpf()).isPresent();

            if (cpfJaCadastrado) {
                throw new ClienteException("CPF '" + clienteDTO.getCpf() + "' já está cadastrado.");
            }
    }

}

