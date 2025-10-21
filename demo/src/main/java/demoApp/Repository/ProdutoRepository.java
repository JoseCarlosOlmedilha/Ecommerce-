package demoApp.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import demoApp.Entities.Produto;
import demoApp.Entities.Enums.EstacaoClimatica;
import demoApp.Entities.Enums.StatusProduto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByCategoria(EstacaoClimatica categoria);

    List<Produto> findByStatusProduto(StatusProduto status);

    Produto findByNome(String nome);

    Boolean existsByNome(String nome);

    @Query(value ="""
            SELECT p.quantidade
            FROM tb_produto p
            WHERE p.id = :id
            """, nativeQuery = true)
    Optional<Integer> findByProdutoQuantidade(@Param("id") Long id);
    
}
