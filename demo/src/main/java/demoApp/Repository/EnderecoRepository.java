package demoApp.Repository;

import org.springframework.stereotype.Repository;

import demoApp.Entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Long> {

}
