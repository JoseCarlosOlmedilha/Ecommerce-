package demoApp.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demoApp.Dto.ClientDTO;
import demoApp.Dto.PhoneDTO;
import demoApp.Entities.Address;
import demoApp.Entities.Client;
import demoApp.Entities.Phone;
import demoApp.Entities.Enums.UfAddress;
import demoApp.Exception.RegisterException;
import demoApp.Repository.ClientRepository;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    public AddressService addressService;


    public ClientDTO registerClient(ClientDTO clientDTO){
        Client client = new Client();

        var cliente = clientRepository.findByCpf(clientDTO.getCpf())
        .orElseThrow( () -> new RegisterException("Não foi possicel cadastrar Usuario, Cpf já cadastrado")) ;

        client = dtoParaClient(clientDTO);

        clientRepository.save(client);

        return clientDTO;
    }

    public Boolean deletarCliente(Long id){

        var cliente = clientRepository.findById(id).orElseThrow(() -> new RegisterException("Não foi possível encontrar o cliente, para excluir"));

        clientRepository.delete(cliente);

        return true;

    }

    public ClientDTO atualizarClient(ClientDTO clientDTO, Long id){
        Client client = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Client clienteAtualizado = dtoParaClient(clientDTO);
        clienteAtualizado.setClienteId(client.getClienteId());

        clientRepository.save(clienteAtualizado);

        return clientToDTO(clienteAtualizado);

    }

    public ClientDTO buscarCliente(Long id){
        ClientDTO clientDto = clientToDTO(clientRepository.findById(id).orElseThrow( () -> new RuntimeException("Cliente não encontrado")));

        return clientDto;
    }

    public List<ClientDTO> buscarTodosClient() {
        return clientRepository.findAll().stream()
            .map(this::clientToDTO)
            .collect(Collectors.toList());
    }

    public Client dtoParaClient(ClientDTO clientDTO) {
        Client client = new Client();
        client.setName(clientDTO.getNome());
        client.setCpf(clientDTO.getCpf());
        client.setEmail(clientDTO.getEmail());
        client.setDateOfBirth(clientDTO.getDateOfBirth());
        client.setSexo(clientDTO.getSexo());

        Address address = addressService.convertDTOtoAddress( clientDTO.getAddress() );
        address.setClient(client);
        client.setAddress(address);
        

        client.setPhone(clientDTO.getPhones().stream()
            .map(dto -> {
                Phone phone = new Phone();
                phone.setNumero(dto.getNumero());
                phone.setTypePhone(dto.getTypePhone());
                phone.setDdd(dto.getDdd());
                phone.setClient(client); 
                return phone;
            }).collect(Collectors.toList())
        );

        return client;
    }

    public ClientDTO clientToDTO(Client client) {
        ClientDTO dto = new ClientDTO();

        dto.setNome(client.getName());
        dto.setCpf(client.getCpf());
        dto.setEmail(client.getEmail());
        dto.setSexo(client.getSexo());
        dto.setDateOfBirth(client.getDateOfBirth());

        Address address = client.getAddress();
        if (address != null) {
            dto.setAddress(addressService.convertToAddressDTO(address));
        }

        if (client.getPhone() != null && !client.getPhone().isEmpty()) {
            List<PhoneDTO> phoneDTOs = client.getPhone().stream()
                .map(this::convertToPhoneDTO)
                .collect(Collectors.toList());
            dto.setPhones(phoneDTOs);
        }

        return dto;
    }

    private PhoneDTO convertToPhoneDTO(Phone phone) {
        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setNumero(phone.getNumero());
        phoneDTO.setDdd(phone.getDdd());
        phoneDTO.setTypePhone(phone.getTypePhone());
        return phoneDTO;
    }

    public List<ClientDTO> buscarClientesPorRegião(UfAddress uf){
        List<Client> client = clientRepository.findClientsByUf(uf.name());
        
        List<ClientDTO> clientDTO = client.stream()
        .map( clien -> clientToDTO(clien))
        .collect(Collectors.toList());


        return clientDTO;
    }



}
