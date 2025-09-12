package demoApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import demoApp.Entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

    @Query(value = """
        SELECT c.nome, c.sexo, c.dateOfBirth, c.email,
            p.typePhone, p.ddd, p.number,
            a.rua, a.city, a.UfAddress, a.number, a.complemento, a.cep, a.bairro
        FROM client c
        INNER JOIN phone p ON p.client_id = c.clienteId
        INNER JOIN address a ON a.client_id = c.clienteId
        WHERE a.UfAddress = :uf
        """, nativeQuery = true)
    List<Object[]> findClientsByUf(@Param("uf") String uf);

} 
