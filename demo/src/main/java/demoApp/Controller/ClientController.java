package demoApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import demoApp.Dto.ClientDTO;
import demoApp.Entities.Client;
import demoApp.Service.ClientService;

@RestController
@RequestMapping(value = "/Client" )
public class ClientController {

@Autowired
private ClientService clientService;

@PostMapping
public ResponseEntity<Client> cadastrar(@RequestBody ClientDTO clientDTO) {
    clientService.registerClient(clientDTO);
    return ResponseEntity.ok().build();
}

@GetMapping("/{id}")
public ResponseEntity<ClientDTO> buscarCliente(@PathVariable Long id){
    ClientDTO client = clientService.buscarCliente(id);
    return ResponseEntity.ok(client);
}






}
