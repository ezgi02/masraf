package security.masraftakip.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import security.masraftakip.model.AggregationResult;
import security.masraftakip.repository.AggregationResultRepository;
import security.masraftakip.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class AggregationService {
    private static final Logger logger = LoggerFactory.getLogger(AggregationService.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AggregationResultRepository aggregationResultRepository;

    @Value("${schedule.daily}")
    private String dailyCron;

    @Value("${schedule.weekly}")
    private String weeklyCron;

    @Value("${schedule.monthly}")
    private String monthlyCron;

    @Scheduled(cron = "0 0 0 * * ?")
    public void aggregateDailySpending() {
        LocalDate today = LocalDate.now();
        logger.info("Running daily aggregation for {}", today);
        aggregateSpending(today, today, "daily");
    }

    @Scheduled(cron = "0 0 0 * * MON")
    public void aggregateWeeklySpending() {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.minusDays(today.getDayOfWeek().getValue() - 1);
        aggregateSpending(startOfWeek, today, "weekly");
    }

    @Scheduled(cron = "0 0 0 1 * ?")
    public void aggregateMonthlySpending() {
        LocalDate today = LocalDate.now();
        LocalDate startOfMonth = today.withDayOfMonth(1);
        aggregateSpending(startOfMonth, today, "monthly");
    }

    private void aggregateSpending(LocalDate startDate, LocalDate endDate, String aggregationType) {
        List<Long> userIds = transactionRepository.findAllUserIds();
        for (Long userId : userIds) {
            BigDecimal totalSpending = transactionRepository.findTotalSpendingByUserIdAndDateRange(userId, startDate, endDate);
            AggregationResult result = new AggregationResult(null, userId, totalSpending, startDate, endDate, aggregationType);
            aggregationResultRepository.save(result);
        }
    }
}
