package ge.bog.luka.repository;

import ge.bog.luka.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProviderRepository extends JpaRepository<Provider, Long> {

    List<Provider> findAll();

}
