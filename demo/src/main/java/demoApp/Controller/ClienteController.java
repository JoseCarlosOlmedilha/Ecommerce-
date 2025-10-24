package demoApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demoApp.Dto.AlterarStatusClienteDTO;
import demoApp.Dto.ClienteDTO;
import demoApp.Dto.Projection.ClientDetailsProjection;
import demoApp.Entities.Enums.UnidadeFederativa;
import demoApp.Service.ClienteService;
import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/cliente" )
public class ClienteController {

@Autowired
private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDTO> cadastrar(@Valid @RequestBody ClienteDTO clientDTO) {
        ClienteDTO client = clienteService.cadastrarCliente(clientDTO);
        return ResponseEntity.ok().body(client);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarCliente( @PathVariable Long id){
        ClienteDTO client = clienteService.buscarCliente(id);
        return ResponseEntity.ok(client);
    }

    @PutMapping("/status")
    public ResponseEntity alterarStatusCliente(@RequestBody AlterarStatusClienteDTO statusCliente){
       
        boolean atualizado = clienteService.alterarStatusCliente(statusCliente.getCpf());
        
        if(atualizado){
            return ResponseEntity.ok("Status atualizado com sucesso");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não atualizado");
        }        
        
    }

    @PutMapping("/atualizar")
    public ResponseEntity<ClienteDTO> atualizarCliente(@Valid @RequestBody ClienteDTO clientDTO){
        ClienteDTO client = clienteService.atualizarCliente(clientDTO);
        return ResponseEntity.ok(client);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> buscarTodosClientes() {
        List<ClienteDTO> clients = clienteService.buscarTodosClientes();
        return ResponseEntity.ok(clients);

    }

    @GetMapping("/clienteUF/{uf}")
    public ResponseEntity<List<ClientDetailsProjection>> buscarClientesPorRegião(@PathVariable String uf) {
        List<ClientDetailsProjection> clients = clienteService.buscarClientesPorRegião(UnidadeFederativa.valueOf(uf.toUpperCase()));
        return ResponseEntity.ok(clients);
    }



}
