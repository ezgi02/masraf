package security.masraftakip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.masraftakip.service.AggregationService;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private AggregationService aggregationService;

    @GetMapping("/run-daily")
    public String runDailyAggregation() {
        aggregationService.aggregateDailySpending();
        return "Daily aggregation triggered!";
    }

    @GetMapping("/run-weekly")
    public String runWeeklyAggregation() {
        aggregationService.aggregateWeeklySpending();
        return "Weekly aggregation triggered!";
    }

    @GetMapping("/run-monthly")
    public String runMonthlyAggregation() {
        aggregationService.aggregateMonthlySpending();
        return "Monthly aggregation triggered!";
    }
}