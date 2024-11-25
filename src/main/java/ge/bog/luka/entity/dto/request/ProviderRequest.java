package ge.bog.luka.entity.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProviderRequest {

    @NotNull
    private String name;

    private String deptCode;

    private String payCode;

    @NotNull
    private Integer maxThreads;

    @NotNull
    private BigDecimal minAmount;

    @NotNull
    private BigDecimal maxAmount;

    @NotNull
    private Long providerGroupId;

    @NotNull
    private boolean active;
}
