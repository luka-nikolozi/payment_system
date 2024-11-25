package ge.bog.luka.repository;

import ge.bog.luka.entity.ProviderGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProviderGroupRepository extends JpaRepository<ProviderGroup, Long> {

    ProviderGroup findByName(String providerName);

    List<ProviderGroup> findAll();

}
