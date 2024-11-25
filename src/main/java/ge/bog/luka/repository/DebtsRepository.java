package ge.bog.luka.repository;

import ge.bog.luka.entity.Debt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface DebtsRepository extends JpaRepository<Debt, Long> {

    Debt findByAbonentCode(String abonentCode);

}
