package demoApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demoApp.Entities.ItensPedido;

@Repository
public interface ItensPedidoRepository extends JpaRepository<ItensPedido, Long> {

}
