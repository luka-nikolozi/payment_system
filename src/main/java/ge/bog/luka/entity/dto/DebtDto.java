package ge.bog.luka.entity.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DebtDto {

    private String abonentCode;

    private BigDecimal amount;
}
