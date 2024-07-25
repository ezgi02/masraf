package security.masraftakip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import security.masraftakip.model.AggregationResult;

import java.util.List;

public interface AggregationResultRepository extends JpaRepository<AggregationResult, Long> {
    List<AggregationResult> findByUserIdAndAggregationType(Long userId, String aggregationType);
}
