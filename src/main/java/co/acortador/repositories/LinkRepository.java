package co.acortador.repositories;

import co.acortador.models.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface LinkRepository extends JpaRepository<Link,Long> {
    Optional<Link> findByCode(String code);
    boolean existsByCodeAndStateIsTrue(String code);
}
