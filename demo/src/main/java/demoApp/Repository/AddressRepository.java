package demoApp.Repository;

import org.springframework.stereotype.Repository;

import demoApp.Entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {

}
