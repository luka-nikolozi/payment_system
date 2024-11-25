package ge.bog.luka.entity;

import ge.bog.luka.entity.additional.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "terminal")
public class Terminal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Embedded
    private Address address;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "last_access_time")
    private LocalDateTime lastAccessTime;

    @OneToMany(mappedBy = "terminal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProviderTerminals> providerTerminals;

    @OneToMany(mappedBy = "terminal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payment> payments;

}
