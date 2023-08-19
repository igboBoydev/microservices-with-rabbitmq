package com.abelkelly.fraud.Controllers;

import com.abelkelly.fraud.FraudRecord.FraudCheckResponse;
import com.abelkelly.fraud.Services.FraudCheckService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/fraud-check")
public class FraudController {
    private final FraudCheckService fraudCheckService;

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudSter(@PathVariable("customerId") Integer customerId){
        System.out.println("====================");
        boolean isFraudSter = fraudCheckService.isFraudulent(customerId);
        System.out.println("fraud = " + isFraudSter);
        log.info("Fraud check request for customer {}", customerId);

        return new FraudCheckResponse(isFraudSter);
    }
}
