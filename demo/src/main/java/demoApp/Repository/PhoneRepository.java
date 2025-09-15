package demoApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demoApp.Entities.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
