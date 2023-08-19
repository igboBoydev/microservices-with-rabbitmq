package com.abelkelly.fraud.Services;

import com.abelkelly.fraud.Model.FraudCheckHistory;
import com.abelkelly.fraud.Repository.FraudCheckHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckService {
    private final FraudCheckHistoryRepository historyRepository;
    public boolean isFraudulent(Integer customerId){
        historyRepository.save(FraudCheckHistory.builder().
                isFraudSter(false).customerId(customerId).
                createdAt(LocalDateTime.now())
                .build());
        return false;
    }
}
