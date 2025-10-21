package demoApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demoApp.Entities.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

}
