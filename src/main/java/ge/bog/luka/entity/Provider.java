package ge.bog.luka.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "provider")
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "debt_code")
    private String debtCode;

    @Column(name = "pay_code")
    private String payCode;

    @Column(name = "max_threads", nullable = false)
    private Integer maxThreads;

    @Column(name = "min_amount", nullable = false)
    private BigDecimal minAmount;

    @Column(name = "max_amount", nullable = false)
    private BigDecimal maxAmount;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @ManyToOne(fetch = FetchType.EAGER)
    private ProviderGroup providerGroup;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
    private List<ProviderTerminals> providerTerminals;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
    private List<Payment> payments;

}
