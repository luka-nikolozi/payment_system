package ge.bog.luka.repository;

import ge.bog.luka.entity.ProviderTerminals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProviderTerminalsRepository extends JpaRepository<ProviderTerminals, Long> {
    List<ProviderTerminals> findByTerminalId(Long terminalId);

    List<ProviderTerminals> findByProviderId(Long providerId);

    List<ProviderTerminals> findAll();

    ProviderTerminals findByProviderIdAndTerminalId(Long providerId, Long terminalId);
}
