package demoApp.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import demoApp.Dto.Projection.ClientDetailsProjection;
import demoApp.Entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

    /* 
    @Query(value = """
        SELECT c.name, c.sexo, c.date_of_birth, c.email,
            p.type_phone, p.ddd, p.numero,
            a.street, a.city, a.uf, a.number, a.complemento, a.cep, a.bairro
        FROM tb_client c
            INNER JOIN tb_phone p ON p.client_id = c.client_id
            INNER JOIN tb_address a ON a.cliente_id = c.client_id
        WHERE a.uf = :uf
        """, nativeQuery = true) 
    List<Client> findClientsByUf(@Param("uf") String uf);
*/

    @Query(value = """
        SELECT c.name AS name, c.sexo AS sexo, c.date_of_birth AS dateOfBirth, c.email AS email,
            p.type_phone AS typePhone, p.ddd AS ddd, p.numero AS numero,
            a.street AS street, a.city AS city, a.uf AS uf, a.number AS number,
            a.complemento AS complemento, a.cep AS cep, a.bairro AS bairro
        FROM tb_client c
        INNER JOIN tb_phone p ON p.client_id = c.client_id
        INNER JOIN tb_address a ON a.cliente_id = c.client_id
        WHERE a.uf = :uf
        """, nativeQuery = true)
    List<ClientDetailsProjection> findClientsByUf(@Param("uf") String uf);

    Optional<Client> findByCpf(String cpf);

} 
