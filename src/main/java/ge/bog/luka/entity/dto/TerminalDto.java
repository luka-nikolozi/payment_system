package ge.bog.luka.entity.dto;

import ge.bog.luka.entity.additional.Address;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TerminalDto {

    private Address address;

    private Boolean active;

    private LocalDateTime lastAccessTime;
}
