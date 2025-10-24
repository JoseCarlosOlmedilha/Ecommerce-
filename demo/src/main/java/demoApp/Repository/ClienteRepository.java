package demoApp.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import demoApp.Dto.Projection.ClientDetailsProjection;
import demoApp.Entities.Cliente;
import demoApp.Entities.Enums.StatusCliente;
import jakarta.transaction.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    @Query(value = """
                    SELECT 
                        c.nome AS nome,
                        c.sexo AS sexo,
                        c.data_aniversario AS dataAniversario,
                        c.email AS email,
                    GROUP_CONCAT(CONCAT(t.tipo_telefone, ': (', t.ddd, ') ', t.numero) SEPARATOR ' | ') AS telefones,
                        e.rua AS rua,
                        e.cidade AS cidade,
                        e.uf AS uf,
                        e.numero_residencial AS numeroResidencial,
                        e.complemento AS complemento,
                        e.cep AS cep,
                        e.bairro AS bairro
                    FROM tb_cliente c
                    INNER JOIN tb_telefone t ON t.client_id = c.cliente_id
                    INNER JOIN tb_endereco e ON e.cliente_id = c.cliente_id
                    WHERE e.uf = :uf
                    GROUP BY c.cliente_id, c.nome, c.sexo, c.data_aniversario, c.email,
                            e.rua, e.cidade, e.uf, e.numero_residencial, e.complemento, e.cep, e.bairro
                    ORDER BY c.nome
                """, nativeQuery = true)
    List<ClientDetailsProjection> findClientesByUf(@Param("uf") String uf);

    Optional<Cliente> findByCpf(String cpf);

    @Query(value = """
            SELECT c.status_cliente
            FROM tb_cliente
            where cpf = :cpf
            """, nativeQuery = true)
    Optional<StatusCliente> findByStatusCliente(@Param("cpf") String cpf);

    @Transactional
    @Modifying
        @Query(value = """
            UPDATE tb_cliente c
            SET c.status_cliente = :status
            WHERE cpf = :cpf
            """, nativeQuery = true)
    int atualizarStatusCliente(@Param("cpf") String cpf, @Param("status") String status);
} 
