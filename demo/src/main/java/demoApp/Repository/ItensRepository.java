package demoApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demoApp.Entities.Itens;
import demoApp.Entities.Enums.StatusItem;

@Repository
public interface ItensRepository extends JpaRepository<Itens, Long> {

    List<Itens> findByCategoria(StatusItem status);

    List<Itens> findByStatusItem(StatusItem status);

    Itens findByNome(String nome);
}
