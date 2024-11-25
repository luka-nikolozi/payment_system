package ge.bog.luka.job;

import ge.bog.luka.aop.LoggingAnnotation;
import ge.bog.luka.entity.Payment;
import ge.bog.luka.entity.additional.PaymentStatus;
import ge.bog.luka.service.PaymentService;
import ge.bog.luka.service.ProviderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentJob {

    private final Map<Long, Executor> threadPool = new HashMap<>();
    private final PaymentService paymentService;
    private final ProviderService providerService;

    @Scheduled(cron = "0 0 3 * * ?")
    @LoggingAnnotation
    public void processPayments() {
        List<Payment> payments = paymentService.getPaymentsByStatus(PaymentStatus.PENDING);

        Map<Long, Executor> executors = new HashMap<>();

        for (Payment payment : payments) {
            Long providerId = payment.getProvider().getId();

            if (!executors.containsKey(providerId) ) {
                Executor executor = Executors.newFixedThreadPool(providerService.getProviderById(providerId).getMaxThreads());
                executors.put(providerId, executor);
            }

            try {
                executors.get(providerId).execute(() -> {
                    paymentService.performPayment(payment);
                });
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

    }

}
