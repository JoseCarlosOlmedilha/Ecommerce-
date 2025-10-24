package demoApp.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import demoApp.Dto.Projection.ClientDetailsProjection;
import demoApp.Entities.Cliente;
import demoApp.Entities.Endereco;
import demoApp.Entities.Telefone;
import demoApp.Entities.Enums.Sexo;
import demoApp.Entities.Enums.StatusCliente;
import demoApp.Entities.Enums.TipoTelefone;
import demoApp.Entities.Enums.UnidadeFederativa;



@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    @DisplayName("Ao passar uma UF deve retornar uma lista de clientes não nula")
    void deve_devolver_uma_lista_nao_nula_com_todos_clientes_de_uma_UF(){

        var endereco = criarEntidadeEndereco("Rua Manoel nobrega", "Santos", UnidadeFederativa.SP,
                                             "524", "305", "11700-140", "Boqueirão");
    
        var telefone = criarEntidadeTelefone(TipoTelefone.CEL, "013", "996918182");

        var cliente = criarEntidadeCliente(StatusCliente.ATIVO, "José Carlos", "359.193.078-46",
                                    "joseolmedilha@hotmail.com", Sexo.MASCULINO, LocalDate.parse("2001-11-24"));


        List<Telefone> telefonesList = new ArrayList<>();
        
        telefone.setCliente(cliente);

        telefonesList.add(telefone);
        cliente.setTelefone(telefonesList);


        cliente.setEndereco(endereco);
        endereco.setCliente(cliente);
        
        

        cadastrarCliente(endereco, cliente, telefonesList);
    
    List<ClientDetailsProjection> clientes = clienteRepository.findClientesByUf("SP");

        System.out.println("Qtd clientes SP: " + clientes.size());
        clientes.forEach(c -> System.out.println(c.getNome()));

    assertThat(clientes).isNotNull();

    }

    @Test
    @DisplayName("Ao passar uma UF deve retornar uma lista não vazia")
    void deve_devolver_uma_lista_não_vazia_com_todos_clientes_de_uma_UF(){

        var endereco = criarEntidadeEndereco("Rua Manoel nobrega", "Santos", UnidadeFederativa.SP,
                                             "524", "305", "11700-140", "Boqueirão");
    
        var telefone = criarEntidadeTelefone(TipoTelefone.CEL, "013", "996918182");

        var cliente = criarEntidadeCliente(StatusCliente.ATIVO, "José Carlos", "359.193.078-46",
                                    "joseolmedilha@hotmail.com", Sexo.MASCULINO, LocalDate.parse("2001-11-24"));


        List<Telefone> telefonesList = new ArrayList<>();
        
        telefone.setCliente(cliente);

        telefonesList.add(telefone);
        cliente.setTelefone(telefonesList);


        cliente.setEndereco(endereco);
        endereco.setCliente(cliente);
        
        

        cadastrarCliente(endereco, cliente, telefonesList);
    
        List<ClientDetailsProjection> clientes = clienteRepository.findClientesByUf("SP");


        System.out.println("Qtd clientes SP: " + clientes.size());
        clientes.forEach(c -> System.out.println(c.getNome()));

        assertThat(clientes).isNotEmpty();

    }

    @Test
    @DisplayName("Ao passar uma UF deve retornar uma lista não vazia")
    void deve_devolver_uma_lista_apenas_com_clientes_da_uf_passada(){

        var endereco = criarEntidadeEndereco("Rua Manoel nobrega", "Santos", UnidadeFederativa.SP,
                                             "524", "305", "11700-140", "Boqueirão");
    
        var telefone = criarEntidadeTelefone(TipoTelefone.CEL, "013", "996918182");

        var cliente = criarEntidadeCliente(StatusCliente.ATIVO, "José Carlos", "612.961.730-54",
                                    "joseolmedilha@hotmail.com", Sexo.MASCULINO, LocalDate.parse("2001-11-24"));


        List<Telefone> telefonesList = new ArrayList<>();
        
        telefone.setCliente(cliente);

        telefonesList.add(telefone);
        cliente.setTelefone(telefonesList);


        cliente.setEndereco(endereco);
        endereco.setCliente(cliente);
        
        

        cadastrarCliente(endereco, cliente, telefonesList);
    
        List<ClientDetailsProjection> clientes = clienteRepository.findClientesByUf("SP");
    
        assertThat(clientes).isNotEmpty();


        assertThat(clientes)
        .allMatch(c -> c.getUf().equalsIgnoreCase("SP"));


    }





    

    private void cadastrarCliente(Endereco endereco, Cliente cliente, List<Telefone> telefone){
        cliente.setEndereco(endereco);
        cliente.setTelefone(telefone);

        entityManager.persist(cliente);
        

    }

    private Endereco criarEntidadeEndereco(String rua,String cidade, UnidadeFederativa uf, String numeroResidencial, String complemento, String cep, String bairro){
        Endereco endereco =  new Endereco();
        
        endereco.setRua(rua);
        endereco.setCidade(cidade);
        endereco.setUf(uf);
        endereco.setNumeroResidencial(numeroResidencial);
        endereco.setComplemento(complemento);
        endereco.setCep(cep);
        endereco.setBairro(bairro);

        return endereco;

    }

    private Cliente criarEntidadeCliente(StatusCliente statusCliente, String nome, String cpf, String email, Sexo sexo,
                                         LocalDate dataAniversario){

        Cliente cliente = new Cliente();

        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setDataAniversario(dataAniversario);
        cliente.setEmail(email);
        cliente.setStatusCliente(statusCliente);
        cliente.setSexo(sexo);

        return cliente;  
    }

    private Telefone criarEntidadeTelefone(TipoTelefone tipoTelefone, String ddd, String numero){
        Telefone telefone = new Telefone();
            telefone.setTipoTelefone(tipoTelefone);
            telefone.setDdd(ddd);
            telefone.setNumero(numero);

        return telefone;
    }
}
