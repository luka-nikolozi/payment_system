package ge.bog.luka.repository;

import ge.bog.luka.entity.ProviderTerminals;
import ge.bog.luka.entity.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TerminalRepository extends JpaRepository<Terminal, Long> {

    List<Terminal> findAll();

}