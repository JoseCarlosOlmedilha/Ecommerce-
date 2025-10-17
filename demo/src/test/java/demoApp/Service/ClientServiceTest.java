package demoApp.Service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import demoApp.Dto.ClientDTO;
import demoApp.Repository.ClientRepository;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @InjectMocks
    ClientService clientService;

    @Mock
    ClientRepository clientRepository;



    @Test
    void test_deve_retornar_uma_lista_de_cliente() {
        List<ClientDTO> clientes = clientService.buscarTodosClient();
    }

}
