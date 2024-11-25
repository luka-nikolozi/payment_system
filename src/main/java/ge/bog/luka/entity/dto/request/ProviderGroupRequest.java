package ge.bog.luka.entity.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProviderGroupRequest {

    @NotNull
    private String name;

    @NotNull
    private String description;
}
