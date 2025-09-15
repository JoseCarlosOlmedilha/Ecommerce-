package demoApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demoApp.Entities.Itens;

@Repository
public interface ItensRepository extends JpaRepository<Itens, Long> {

}
