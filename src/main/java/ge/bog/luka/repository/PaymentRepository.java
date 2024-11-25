package ge.bog.luka.repository;


import ge.bog.luka.entity.Payment;
import ge.bog.luka.entity.additional.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByStatus(PaymentStatus paymentStatus);

    List<Payment> findByAbonentCodeAndStatus (String abonentCode, PaymentStatus status);

}
