package demoApp.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demoApp.Dto.EnderecoDTO;
import demoApp.Dto.ClienteDTO;
import demoApp.Dto.TelefoneDTO;
import demoApp.Dto.Projection.ClientDetailsProjection;
import demoApp.Entities.Endereco;
import demoApp.Entities.Cliente;
import demoApp.Entities.Telefone;
import demoApp.Entities.Enums.StatusCliente;
import demoApp.Entities.Enums.UnidadeFederativa;
import demoApp.Exception.ClienteException;
import demoApp.Repository.ClienteRepository;
import demoApp.Validation.ClienteValidation.ValidadorCliente;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    public EnderecoService enderecoService;

    @Autowired
    public TelefoneService telefoneService;

    @Autowired
    private List<ValidadorCliente> validadorClientes;

    public ClienteDTO cadastrarCliente(ClienteDTO clienteDTO){

        validadorClientes.forEach(v -> v.validar(clienteDTO));
        
        Cliente cliente = converterDTOParaCliente(clienteDTO);
        cliente.setStatusCliente(StatusCliente.ATIVO);

        clienteRepository.save(cliente);

        return clienteDTO;
    }


    public Boolean alterarStatusCliente(String cpf){

            Cliente cliente = clienteRepository.findByCpf(cpf)
        .orElseThrow(() -> new ClienteException("Cliente para desativação não encontrado"));

        System.out.println(cliente.toString());

        if(cliente.getStatusCliente() == StatusCliente.DESATIVADO){
                clienteRepository.atualizarStatusCliente(cpf,"ATIVO");
        }
        else{
            clienteRepository.atualizarStatusCliente(cpf, "DESATIVADO");
        }

        System.out.println(cliente.toString());
        return true;

    }


    public ClienteDTO atualizarCliente(ClienteDTO clienteDTO){
        Cliente cliente = clienteRepository.findByCpf(clienteDTO.getCpf()).orElseThrow(() -> 
            new ClienteException("Cliente não encontrado para atualização")
        );

        cliente = atualizaClienteComDTO(cliente, clienteDTO);

        clienteRepository.save(cliente);

        return converterClienteParaDto(cliente);
    }
    

    public Cliente atualizaClienteComDTO(Cliente cliente, ClienteDTO clienteDTO) {
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setDataAniversario(clienteDTO.getDataAniversario());
        cliente.setSexo(clienteDTO.getSexo());

        // Atualizar endereço existente, ou criar se null
        if (cliente.getEndereco() == null) {
            Endereco novoEndereco = enderecoService.converterDTOParaEndereco(clienteDTO.getEndereco());
            novoEndereco.setCliente(cliente);
            cliente.setEndereco(novoEndereco);
        } else {
            Endereco enderecoExistente = cliente.getEndereco();
            EnderecoDTO enderecoDTO = clienteDTO.getEndereco();
            
            enderecoExistente.setRua(enderecoDTO.getRua());
            enderecoExistente.setCidade(enderecoDTO.getCidade());
            enderecoExistente.setUf(enderecoDTO.getUf());
            enderecoExistente.setNumeroResidencial(enderecoDTO.getNumero());
            enderecoExistente.setComplemento(enderecoDTO.getComplemento());
            enderecoExistente.setCep(enderecoDTO.getCep());
            enderecoExistente.setBairro(enderecoDTO.getBairro());
        }

        // Atualizar telefones — simplificado: limpar e adicionar os novos
        cliente.getTelefone().clear();

        List<Telefone> telefonesAtualizados = clienteDTO.getTelefone().stream()
            .map(dto -> {
                Telefone telefone = new Telefone();
                telefone.setNumero(dto.getNumero());
                telefone.setTipoTelefone(dto.getTipoTelefone());
                telefone.setDdd(dto.getDdd());
                telefone.setCliente(cliente);
                return telefone;
            }).collect(Collectors.toList());

        cliente.getTelefone().addAll(telefonesAtualizados);

        return cliente;
    }

    public ClienteDTO buscarCliente(Long id){
        ClienteDTO clientDto = converterClienteParaDto(clienteRepository.findById(id).orElseThrow( () -> 
            new ClienteException("Cliente não encontrado"))
        );
        

        return clientDto;
    }

    public List<ClienteDTO> buscarTodosClientes() {
        return 
            clienteRepository.findAll().stream()
            .map(this::converterClienteParaDto )
            .collect(Collectors.toList());
    }

    public Cliente converterDTOParaCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setDataAniversario(clienteDTO.getDataAniversario());
        cliente.setSexo(clienteDTO.getSexo());

        Endereco endereco = enderecoService.converterDTOParaEndereco( clienteDTO.getEndereco() );
        endereco.setCliente(cliente);
        cliente.setEndereco(endereco);
        

        cliente.setTelefone(clienteDTO.getTelefone().stream()
            .map(dto -> {
                Telefone telefone = new Telefone();
                telefone.setNumero(dto.getNumero());
                telefone.setTipoTelefone(dto.getTipoTelefone());
                telefone.setDdd(dto.getDdd());
                telefone.setCliente(cliente); 
                return telefone;
            }).collect(Collectors.toList())
        );

        return cliente;
    }

    public ClienteDTO converterClienteParaDto(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();

        dto.setNome(cliente.getNome());
        dto.setCpf(cliente.getCpf());
        dto.setEmail(cliente.getEmail());
        dto.setSexo(cliente.getSexo());
        dto.setDataAniversario(cliente.getDataAniversario());
        dto.setStatusCliente(cliente.getStatusCliente());

        Endereco endereco = cliente.getEndereco();
        if (endereco != null) {
            dto.setEndereco(enderecoService.converterEnderecoParaEnderecoDTO(endereco));
        }

        if (cliente.getTelefone() != null && !cliente.getTelefone().isEmpty()) {
            List<TelefoneDTO> telefoneDTOs = cliente.getTelefone().stream()
                .map(telefoneService::converterToTelefoneDTO)
                .collect(Collectors.toList());
            dto.setTelefone(telefoneDTOs);
        }

        return dto;
    }

    public List<ClientDetailsProjection> buscarClientesPorRegião(UnidadeFederativa uf) {
        return clienteRepository.findClientesByUf(uf.name());
    }



}
