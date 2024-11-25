package ge.bog.luka.entity.dto.request;

import ge.bog.luka.entity.additional.Address;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TerminalRequest {

    @NotNull
    private Address address;

    @NotNull
    private Boolean active;

    @NotNull
    private LocalDateTime lastAccessTime;
}
