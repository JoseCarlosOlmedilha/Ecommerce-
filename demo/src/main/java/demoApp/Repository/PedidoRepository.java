package demoApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import demoApp.Entities.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {


     @Query(value = """
        SELECT p.id, e.produto
        FROM Pedido p
        JOIN ProdutoPedido e ON p.id = e.pedido.id
        WHERE p.cliente.id = :id
        """, nativeQuery = false)
    List<Pedido> findPedidoByClients(@Param("id") Long id);

    List<Pedido> findByClienteId(Long clienteId);

}
