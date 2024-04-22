package ee.tbot.apartmentbot.repository;

import ee.tbot.apartmentbot.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentRepo extends JpaRepository<Apartment, Long> {
}
