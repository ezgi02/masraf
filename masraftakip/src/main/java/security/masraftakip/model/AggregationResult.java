package security.masraftakip.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "aggregation_results")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AggregationResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private BigDecimal totalSpending;

    private LocalDate startDate;

    private LocalDate endDate;

    private String aggregationType;//daily,weekly,monthly
}
