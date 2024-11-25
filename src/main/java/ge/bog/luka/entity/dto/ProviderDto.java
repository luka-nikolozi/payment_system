package ge.bog.luka.entity.dto;

import ge.bog.luka.Mapper.ProviderMapper;
import ge.bog.luka.entity.Provider;
import ge.bog.luka.entity.ProviderGroup;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProviderDto {
    private String name;
    private String deptCode;
    private String payCode;
    private Integer maxThreads;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private Long providerGroupId;
    private boolean active;
}
