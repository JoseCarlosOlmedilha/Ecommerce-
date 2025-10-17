package demoApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demoApp.Entities.Produto;
import demoApp.Entities.Enums.Categoria;
import demoApp.Entities.Enums.StatusProduto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByCategoria(Categoria categoria);

    List<Produto> findByStatusProduto(StatusProduto status);

    Produto findByNome(String nome);

    Boolean existsByNome(String nome);
}
