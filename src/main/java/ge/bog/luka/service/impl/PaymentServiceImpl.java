package ge.bog.luka.service.impl;


import ge.bog.luka.aop.LoggingAnnotation;
import ge.bog.luka.entity.*;
import ge.bog.luka.entity.additional.PaymentStatus;
import ge.bog.luka.entity.dto.request.PayRequest;
import ge.bog.luka.repository.*;
import ge.bog.luka.service.PaymentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final DebtsRepository debtsRepository;
    private final PaymentRepository paymentRepository;
    private final TerminalRepository terminalRepository;
    private final ProviderRepository providerRepository;
    private final ProviderTerminalsRepository providerTerminalsRepository;

    @Override
    public Debt getDebt(String abonentCode) {
        Debt debt = debtsRepository.findByAbonentCode(abonentCode);
        if (debt == null) {
            throw new EntityNotFoundException("there is no debt on this abonentCode");
        }
        return debt;
    }

    @Override
    public Payment createPayment(PayRequest payRequest) {

        Terminal terminal = terminalRepository.findById(payRequest.getTerminalId())
                .orElseThrow(() -> new EntityNotFoundException("Terminal not found"));
        Provider provider = providerRepository.findById(payRequest.getProviderId())
                .orElseThrow(() -> new EntityNotFoundException("Provider not found"));

        Payment payment = new Payment();
        payment.setTerminal(terminal);
        payment.setProvider(provider);
        payment.setAbonentCode(payRequest.getAbonentCode());
        payment.setAmount(payRequest.getAmount());
        payment.setStatus(PaymentStatus.CREATED);
        payment.setCreatedAt(LocalDateTime.now());

        Debt debt = debtsRepository.findByAbonentCode(payRequest.getAbonentCode());

        if (debt == null) {
            throw new EntityNotFoundException("there is no debt on this abonentCode");
        } else if (!debt.getAmount().equals(payRequest.getAmount())) {
            throw new EntityNotFoundException("wrong amount");
        } else if (debt.getAmount().compareTo(provider.getMaxAmount()) > 0 ||
                   debt.getAmount().compareTo(provider.getMinAmount()) < 0) {
            throw new EntityNotFoundException("payment amount is beyond provider's amount range");
        } else payment.setStatus(PaymentStatus.PENDING);

        List<Payment> executedPayment = paymentRepository.findByAbonentCodeAndStatus(payRequest.getAbonentCode(), PaymentStatus.PENDING);

        if (!executedPayment.isEmpty()) {
            throw new EntityNotFoundException("this payment is pending");
        }

        return paymentRepository.save(payment);

    }

    @Override
    public List<Payment> getPaymentsByStatus(PaymentStatus paymentStatus) {
        return paymentRepository.findByStatus(paymentStatus);
    }

    @Override
    @Transactional
    @LoggingAnnotation
    public PaymentStatus performPayment(Payment payment) {

        ProviderTerminals providerTerminals = providerTerminalsRepository.findByProviderIdAndTerminalId(payment.getProvider().getId(), payment.getTerminal().getId());


        if (providerTerminals == null) {
            payment.setStatus(PaymentStatus.REJECTED);
        }
        else {
            payment.setStatus(PaymentStatus.PERFORMED);
            debtsRepository.deleteById(debtsRepository.findByAbonentCode(payment.getAbonentCode()).getId());
        }

        return paymentRepository.save(payment).getStatus();

    }

}