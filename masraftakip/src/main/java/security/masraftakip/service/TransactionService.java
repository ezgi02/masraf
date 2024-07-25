package security.masraftakip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import security.masraftakip.model.Transaction;
import security.masraftakip.repository.TransactionRepository;

import java.math.BigDecimal;
import java.util.List;
@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }
    public Transaction getById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
    public List<Transaction> getTransactionsByUserId(Long userId) {
        return transactionRepository.findByUserId(userId);
    }
    public Transaction updateTransaction(Long id,Transaction transaction) {
        Transaction updatedTransaction = transactionRepository.findById(id).orElse(null);
        if (updatedTransaction != null) {
            updatedTransaction.setDescription(transaction.getDescription());
            updatedTransaction.setAmount(transaction.getAmount());
            updatedTransaction.setDate(transaction.getDate());
            return transactionRepository.save(updatedTransaction);
        }
        return null;
    }

    public void delete(Long id) {
        transactionRepository.deleteById(id);
    }
    public BigDecimal getTotalSpendingByUserId(Long userId) {
        List<Transaction> transactions = transactionRepository.findByUserId(userId);
        return transactions.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
