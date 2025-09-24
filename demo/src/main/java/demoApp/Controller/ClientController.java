package demoApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import demoApp.Dto.ClientDTO;
import demoApp.Entities.Enums.UfAddress;
import demoApp.Service.ClientService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value = "/Client" )
public class ClientController {

@Autowired
private ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientDTO> cadastrar(@Valid @RequestBody ClientDTO clientDTO) {
        ClientDTO client = clientService.registerClient(clientDTO);
        return ResponseEntity.ok().body(client);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> buscarCliente( @PathVariable Long id){
        ClientDTO client = clientService.buscarCliente(id);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarCliente(@PathVariable Long id){
        clientService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> atualizarCliente(@Valid @RequestBody ClientDTO clientDTO, @PathVariable Long id){
        ClientDTO client = clientService.atualizarClient(clientDTO, id);
        return ResponseEntity.ok(client);
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> buscarTodosClientes() {
        List<ClientDTO> clients = clientService.buscarTodosClient();
        return ResponseEntity.ok(clients);

    }

    @GetMapping("/ClienteUF")
    public ResponseEntity<List<ClientDTO>> buscarClientesPorRegião(@RequestParam String uf) {
        List<ClientDTO> clients = clientService.buscarClientesPorRegião(UfAddress.valueOf(uf.toUpperCase()));
        return ResponseEntity.ok(clients);
    }


}
