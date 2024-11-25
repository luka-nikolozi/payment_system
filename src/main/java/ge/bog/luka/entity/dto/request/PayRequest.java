package ge.bog.luka.entity.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PayRequest {
    @NotNull
    private Long providerId;

    @NotNull
    private Long terminalId;

    @NotNull
    private String abonentCode;

    @NotNull
    private BigDecimal amount;
}


