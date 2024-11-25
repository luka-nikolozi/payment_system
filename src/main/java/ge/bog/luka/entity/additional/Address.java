package ge.bog.luka.entity.additional;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Address {
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String city;
}
