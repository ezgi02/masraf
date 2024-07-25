package security.masraftakip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import security.masraftakip.model.AggregationResult;
import security.masraftakip.repository.AggregationResultRepository;

import java.util.List;

@RestController
@RequestMapping("/api/aggregations")
public class AggregationController {

    @Autowired
    private AggregationResultRepository aggregationResultRepository;

    @GetMapping("/user/{userId}/type/{aggregationType}")
    public List<AggregationResult> getAggregationResults(@PathVariable Long userId, @PathVariable String aggregationType) {
        return aggregationResultRepository.findByUserIdAndAggregationType(userId, aggregationType);
    }
}
