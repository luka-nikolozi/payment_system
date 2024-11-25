package ge.bog.luka.entity.dto;

import ge.bog.luka.entity.Provider;
import ge.bog.luka.entity.Terminal;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProviderTerminalsDto {

    private Long terminalId;

    private Long providerId;
}
