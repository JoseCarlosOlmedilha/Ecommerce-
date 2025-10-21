package demoApp.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import demoApp.Entities.Cliente;
import demoApp.Entities.Endereco;
import demoApp.Entities.Telefone;
import demoApp.Entities.Enums.Sexo;
import demoApp.Entities.Enums.StatusCliente;
import demoApp.Entities.Enums.TipoTelefone;
import demoApp.Entities.Enums.UnidadeFederativa;
import jakarta.persistence.EntityManager;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClienteRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ClienteRepository clienteRepository;


    @Test
    void deve_atualizar_statusCliente_De_Ativo_para_Desativo() {
        
        Endereco endereco = new Endereco();
        endereco.setRua("Rua pinheiro machado");
        endereco.setCidade("Praia Grande");
        endereco.setUf(UnidadeFederativa.SP);
        endereco.setNumeroResidencial("524");                                        
        endereco.setComplemento("305");
        endereco.setCep("11700-150");
        endereco.setBairro("Boqueirão");

        Telefone telefoneCliente = new Telefone();
        telefoneCliente.setTipoTelefone(TipoTelefone.CEL);
        telefoneCliente.setNumero("996918182");
        telefoneCliente.setDdd("013");

        List<Telefone> telefones = new ArrayList<>();
        telefones.add(telefoneCliente);

        Cliente cliente = new Cliente();
        cliente.setNome("Manoel");
        cliente.setCpf("841.844.980-27");
        cliente.setStatusCliente(StatusCliente.ATIVO);
        cliente.setEmail("manoel@exemplo.com");
        cliente.setSexo(Sexo.MASCULINO);
        cliente.setDataAniversario(LocalDate.parse("2001-12-10"));
        cliente.setEndereco(endereco);
        cliente.setTelefone(telefones);

        // associa o cliente ao endereço (bidirecional)
        endereco.setCliente(cliente);

        // associa o cliente ao telefone (bidirecional)
        telefoneCliente.setCliente(cliente);

        // salva o cliente junto com endereço e telefone
        clienteRepository.save(cliente);

        // atualiza status do cliente
        clienteRepository.atualizarStatusCliente(cliente.getCpf(), "DESATIVADO");

        Cliente clienteDepoisAtualizacao = clienteRepository.findByCpf(cliente.getCpf()).get();

        Assertions.assertEquals(StatusCliente.DESATIVADO, clienteDepoisAtualizacao.getStatusCliente());
    }

    @Test
    void deve_atualizar_statusCliente_De_Desativo_Para_Ativo() {
        
        Endereco endereco = new Endereco();
        endereco.setRua("Rua pinheiro machado");
        endereco.setCidade("Praia Grande");
        endereco.setUf(UnidadeFederativa.SP);
        endereco.setNumeroResidencial("524");                                        
        endereco.setComplemento("305");
        endereco.setCep("11700-150");
        endereco.setBairro("Boqueirão");

        Telefone telefoneCliente = new Telefone();
        telefoneCliente.setTipoTelefone(TipoTelefone.CEL);
        telefoneCliente.setNumero("996918182");
        telefoneCliente.setDdd("013");

        List<Telefone> telefones = new ArrayList<>();
        telefones.add(telefoneCliente);

        Cliente cliente = new Cliente();
        cliente.setNome("Manoel");
        cliente.setCpf("841.844.980-27");
        cliente.setStatusCliente(StatusCliente.DESATIVADO);
        cliente.setEmail("manoel@exemplo.com");
        cliente.setSexo(Sexo.MASCULINO);
        cliente.setDataAniversario(LocalDate.parse("2001-12-10"));
        cliente.setEndereco(endereco);
        cliente.setTelefone(telefones);

        // associa o cliente ao endereço (bidirecional)
        endereco.setCliente(cliente);

        // associa o cliente ao telefone (bidirecional)
        telefoneCliente.setCliente(cliente);

        // salva o cliente junto com endereço e telefone
        clienteRepository.save(cliente);

        clienteRepository.atualizarStatusCliente(cliente.getCpf(), "ATIVO");

        Cliente clienteDepoisAtualizacao = clienteRepository.findByCpf(cliente.getCpf()).get();

        Assertions.assertEquals(StatusCliente.ATIVO, clienteDepoisAtualizacao.getStatusCliente());
                                    
    }

    @Test
    void deve_retornar_o_cliente_pelo_cpf() {

        Endereco endereco = new Endereco();
        endereco.setRua("Rua pinheiro machado");
        endereco.setCidade("Praia Grande");
        endereco.setUf(UnidadeFederativa.SP);
        endereco.setNumeroResidencial("524");                                        
        endereco.setComplemento("305");
        endereco.setCep("11700-150");
        endereco.setBairro("Boqueirão");

        Telefone telefoneCliente = new Telefone();
        telefoneCliente.setTipoTelefone(TipoTelefone.CEL);
        telefoneCliente.setNumero("996918182");
        telefoneCliente.setDdd("013");

        List<Telefone> telefones = new ArrayList<>();
        telefones.add(telefoneCliente);

        Cliente cliente = new Cliente();
        cliente.setNome("Manoel");
        cliente.setCpf("841.844.980-27");
        cliente.setStatusCliente(StatusCliente.ATIVO);
        cliente.setEmail("manoel@exemplo.com");
        cliente.setSexo(Sexo.MASCULINO);
        cliente.setDataAniversario(LocalDate.parse("2001-12-10"));
        cliente.setEndereco(endereco);
        cliente.setTelefone(telefones);

        // associa o cliente ao endereço (bidirecional)
        endereco.setCliente(cliente);

        // associa o cliente ao telefone (bidirecional)
        telefoneCliente.setCliente(cliente);

        // salva o cliente junto com endereço e telefone
        clienteRepository.save(cliente);

        Cliente clienteBD = clienteRepository.findByCpf(cliente.getCpf()).get();

        Assertions.assertEquals(cliente.getNome(), clienteBD.getNome());
        
    }

    @Test
    void nao_deve_retornar_o_cpf() {
        Endereco endereco = new Endereco();
        endereco.setRua("Rua pinheiro machado");
        endereco.setCidade("Praia Grande");
        endereco.setUf(UnidadeFederativa.SP);
        endereco.setNumeroResidencial("524");                                        
        endereco.setComplemento("305");
        endereco.setCep("11700-150");
        endereco.setBairro("Boqueirão");

        Telefone telefoneCliente = new Telefone();
        telefoneCliente.setTipoTelefone(TipoTelefone.CEL);
        telefoneCliente.setNumero("996918182");
        telefoneCliente.setDdd("013");

        List<Telefone> telefones = new ArrayList<>();
        telefones.add(telefoneCliente);

        Cliente cliente = new Cliente();
        cliente.setNome("Manoel");
        cliente.setCpf("841.844.980-27");
        cliente.setStatusCliente(StatusCliente.ATIVO);
        cliente.setEmail("manoel@exemplo.com");
        cliente.setSexo(Sexo.MASCULINO);
        cliente.setDataAniversario(LocalDate.parse("2001-12-10"));
        cliente.setEndereco(endereco);
        cliente.setTelefone(telefones);

        // associa o cliente ao endereço (bidirecional)
        endereco.setCliente(cliente);

        // associa o cliente ao telefone (bidirecional)
        telefoneCliente.setCliente(cliente);

        // salva o cliente junto com endereço e telefone
        clienteRepository.save(cliente);

        String cpfInexistente = "058.530.470-00"; // CPF que não foi salvo
        Optional<Cliente> resultado = clienteRepository.findByCpf(cpfInexistente);

        Assertions.assertTrue(resultado.isEmpty(), "Nenhum cliente deveria ser retornado para CPF inexistente");

    }

    @Test
    void devolver_todos_clientes_pelo_uf() {


    }

    @Test
    void devolver_status_cliente(){

    }



}
