package security.masraftakip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import security.masraftakip.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserId(Long userId);
    @Query("SELECT t.user.id FROM Transaction t GROUP BY t.user.id")
    List<Long> findAllUserIds();

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.user.id = :userId AND t.date BETWEEN :startDate AND :endDate")
    BigDecimal findTotalSpendingByUserIdAndDateRange(Long userId, LocalDate startDate, LocalDate endDate);
}
