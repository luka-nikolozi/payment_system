package ge.bog.luka.entity.dto.request;

import ge.bog.luka.entity.Provider;
import ge.bog.luka.entity.Terminal;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProviderTerminalsRequest {

    @NotNull
    private Long terminalid;

    @NotNull
    private Long providerid;
}
