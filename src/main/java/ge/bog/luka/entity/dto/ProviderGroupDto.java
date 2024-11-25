package ge.bog.luka.entity.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProviderGroupDto {

    @NonNull
    private String name;

    @NonNull
    private String description;
}
