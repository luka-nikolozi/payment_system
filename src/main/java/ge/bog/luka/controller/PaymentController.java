package ge.bog.luka.controller;


import ge.bog.luka.Mapper.DebtMapper;
import ge.bog.luka.entity.Payment;
import ge.bog.luka.entity.additional.PaymentStatus;
import ge.bog.luka.entity.dto.DebtDto;
import ge.bog.luka.entity.dto.request.PayRequest;
import ge.bog.luka.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;
    private final DebtMapper debtMapper;

    @GetMapping("/debt-verify")
    public ResponseEntity<DebtDto> debtVerify(@RequestParam(name = "abonentCode") String abonentCode){
        return ResponseEntity.ok(debtMapper.mapDebt(paymentService.getDebt(abonentCode)));
    }

    @PostMapping("/pay-debt")
    public ResponseEntity<PaymentStatus> pay(@Valid @RequestBody PayRequest payRequest){
        Payment payment = paymentService.createPayment(payRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment.getStatus());
    }
}

















