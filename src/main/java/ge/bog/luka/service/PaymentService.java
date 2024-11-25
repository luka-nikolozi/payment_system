package ge.bog.luka.service;



import ge.bog.luka.entity.Debt;
import ge.bog.luka.entity.Payment;
import ge.bog.luka.entity.additional.PaymentStatus;
import ge.bog.luka.entity.dto.request.PayRequest;

import java.util.List;

public interface PaymentService {

    Debt getDebt(String abonentCode);

    Payment createPayment(PayRequest payRequest);

    List<Payment> getPaymentsByStatus(PaymentStatus paymentStatus);

    PaymentStatus performPayment(Payment payment);

}